package astart;

import astart.DiamondPosition;
import java.util.ArrayList;
import java.util.List;

public class AutoFindWay {
    public static DiamondPosition beginFk = null;
    public static DiamondPosition endFk = null;

    public List<DiamondPosition> getWayLine(MyPanel cat, MyPanel fish) {
        List<DiamondPosition> wayList = new ArrayList<>();
        List<DiamondPosition> tmpList = null;

        beginFk = new DiamondPosition(cat);
        beginFk.setG(0);
        endFk = new DiamondPosition(fish);


        tmpList = aroundFk(beginFk);
        if (tmpList == null || tmpList.size() == 0) {
            return wayList;
        }
        BasePanel.openList.addAll(tmpList);

        for (int i = 0; i < BasePanel.openList.size(); i++) {
            DiamondPosition tmpFk = BasePanel.openList.get(i);
            tmpList = aroundFk(tmpFk);

            if (tmpList == null || tmpList.size() == 0) {
                continue;
            }

            if (tmpList.contains(endFk)) {
                for (DiamondPosition obj : tmpList) {
                    if (obj.equals(endFk)) {
                        BasePanel.closedList.add(obj);
                        break;
                    }
                }
                break;
            }

            for (DiamondPosition fk : tmpList) {
                if (BasePanel.openList.contains(fk)) {
                    for (DiamondPosition openFk : BasePanel.openList) {
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

     
    public List<DiamondPosition> aroundFk(DiamondPosition fk) {
        if (fk.getX() == 10 && fk.getY() == 11) {
            System.out.println(".....");
        }
        List<DiamondPosition> list = new ArrayList<DiamondPosition>();
        if (fk.getY() - 1 >= 0) {
            DiamondPosition tmpFk = new DiamondPosition(fk.getX(), fk.getY() - 1, fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }

        if (fk.getY() + 1 < BasePanel.heightLength) {
            DiamondPosition tmpFk = new DiamondPosition(fk.getX(), fk.getY() + 1, fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }
        if (fk.getX() - 1 >= 0) {
            DiamondPosition tmpFk = new DiamondPosition(fk.getX() - 1, fk.getY(), fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }
        if (fk.getX() + 1 < BasePanel.widthLength) {
            DiamondPosition tmpFk = new DiamondPosition(fk.getX() + 1, fk.getY(), fk);
            if (!BasePanel.zhangaiList.contains(tmpFk) && !BasePanel.closedList.contains(tmpFk)) {
                list.add(tmpFk);
            }
        }

        BasePanel.closedList.add(fk);
        getFGH(list, fk);
        return list;
    }

    public void getFGH(List<DiamondPosition> list, DiamondPosition currFk) {
        if (list != null && list.size() > 0) {
            for (DiamondPosition fk : list) {
                fk.setG(currFk.getG() + 1);
                fk.setH(toGetH(fk, endFk));
                fk.setF(fk.getG() + fk.getH());
            }
        }
    }

    public int toGetH(DiamondPosition currentFangKuai, DiamondPosition targetFangKuai) {
        int h = 0;
        h += Math.abs(currentFangKuai.getX() - targetFangKuai.getX());
        h += Math.abs(currentFangKuai.getY() - targetFangKuai.getY());
        return h;
    }
}
