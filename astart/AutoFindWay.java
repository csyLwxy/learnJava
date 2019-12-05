package astart;

import astart.FangKuaiPosition;
import java.util.ArrayList;
import java.util.List;

public class AutoFindWay {
    public static FangKuaiPosition beginFk = null;
    public static FangKuaiPosition endFk = null;

    public static void main(String[] args) {
        AutoFindWay afw = new AutoFindWay();
        MyPanel cat = new MyPanel(4, 6);
        MyPanel fish = new MyPanel(10, 10);
        afw.getWayLine(cat, fish);
    }

    public List<FangKuaiPosition> getWayLine(MyPanel cat, MyPanel fish) {
        List<FangKuaiPosition> wayList = new ArrayList<>();
        List<FangKuaiPosition> tmpList = null;

        beginFk = new FangKuaiPosition(cat);
        beginFk.setG(0);
        endFk = new FangKuaiPosition(fish);


        tmpList = aroundFk(beginFk);
        if (tmpList == null || tmpList.size() == 0) {
            return wayList;
        }
        BasePanel.openList.addAll(tmpList);

        for (int i = 0; i < BasePanel.openList.size(); i++) {
            FangKuaiPosition tmpFk = BasePanel.openList.get(i);
            tmpList = aroundFk(tmpFk);

            if (tmpList == null || tmpList.size() == 0) {
                continue;
            }

            if (tmpList.contains(endFk)) {
                for (FangKuaiPosition obj : tmpList) {
                    if (obj.equals(endFk)) {
                        BasePanel.closedList.add(obj);
                        break;
                    }
                }
                break;
            }

            for (FangKuaiPosition fk : tmpList) {
                if (BasePanel.openList.contains(fk)) {
                    for (FangKuaiPosition openFk : BasePanel.openList) {
                        if (openFk.equals(fk)) {
                            if (openFk.getG() > fk.getG()) {
                                openFk.setG(fk.getG());
                                openFk.setF(openFk.getG() + openFk.getH());
                                openFk.setPreviousFK(fk.getPreviousFK());
                                break;
                            }
                        }
                    }
                } else {
                    BasePanel.openList.add(fk);
                }
            }

            BasePanel.openList.remove(i);
            i--;
        }

        for (int i = 0; i < BasePanel.closedList.size(); i++) {
            if (wayList.size() > 0) {
                if (wayList.get(wayList.size() - 1).getPreviousFK().equals(BasePanel.closedList.get(i))) {
                    wayList.add(BasePanel.closedList.get(i));
                    if (BasePanel.closedList.get(i).equals(beginFk)) {
                        break;
                    }
                    BasePanel.closedList.remove(BasePanel.closedList.get(i));
                    i = -1;

                }
                continue;
            }

            if (BasePanel.closedList.get(i).equals(endFk)) {
                wayList.add(BasePanel.closedList.get(i));
                BasePanel.closedList.remove(BasePanel.closedList.get(i));
                i = -1;
                continue;
            }
        }

        return wayList;
    }

     
    public List<FangKuaiPosition> aroundFk(FangKuaiPosition fk) {
        if (fk.getX() == 10 && fk.getY() == 11) {
            System.out.println(".....");
        }
        List<FangKuaiPosition> list = new ArrayList<FangKuaiPosition>();
        if (fk.getY() - 1 >= 0) {
            FangKuaiPosition tmpFk = new FangKuaiPosition(fk.getX(), fk.getY() - 1, fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }

        if (fk.getY() + 1 < BasePanel.heightLength) {
            FangKuaiPosition tmpFk = new FangKuaiPosition(fk.getX(), fk.getY() + 1, fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }
        if (fk.getX() - 1 >= 0) {
            FangKuaiPosition tmpFk = new FangKuaiPosition(fk.getX() - 1, fk.getY(), fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }
        if (fk.getX() + 1 < BasePanel.widthLength) {
            FangKuaiPosition tmpFk = new FangKuaiPosition(fk.getX() + 1, fk.getY(), fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }

        BasePanel.closedList.add(fk);
        getFGH(list, fk);
        return list;
    }

    public void getFGH(List<FangKuaiPosition> list, FangKuaiPosition currFk) {
        if (list != null && list.size() > 0) {
            for (FangKuaiPosition fk : list) {
                fk.setG(currFk.getG() + 1);
                fk.setH(toGetH(fk, endFk));
                fk.setF(fk.getG() + fk.getH());
            }
        }
    }

    public int toGetH(FangKuaiPosition currentFangKuai, FangKuaiPosition targetFangKuai) {
        int h = 0;
        h += Math.abs(currentFangKuai.getX() - targetFangKuai.getX());
        h += Math.abs(currentFangKuai.getY() - targetFangKuai.getY());
        return h;
    }
}
