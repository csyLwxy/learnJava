package algorithm.astart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

/**
 * @author web
 */
public class BasePanel extends JFrame {
    private static final long serialVersionUID = 1L;
    static int widthLength = 16;
    static int heightLength = 12;
    private static BackGroundPanel bgp = new BackGroundPanel();

    private static MyPanel cat = new MyPanel(0, 0);
    private static MyPanel fish = new MyPanel(ThreadLocalRandom.current().nextInt(widthLength),
            ThreadLocalRandom.current().nextInt(heightLength));

    static List<DiamondPosition> aiList = new ArrayList<>();
    static List<DiamondPosition> closedList = new ArrayList<>();
    static List<DiamondPosition> openList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        getAiList();
        BasePanel bp = new BasePanel();
        AutoFindWay afw = new AutoFindWay();
        List<DiamondPosition> wayList = afw.getWayLine(cat, fish);
        bp.movePanel(wayList);
    }

    private BasePanel() {
        
        Dimension dimension = CommonUtil.getScreenSize();

        int beginX = (int) (dimension.getWidth() / 2 - 400);
        int beginY = (int) (dimension.getHeight() / 2 - 300);
        int frameHeight = 635;
        int frameWidth = 815;
        this.setBounds(beginX, beginY, frameWidth, frameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        cat.setBackground(Color.green);
        
        fish.setBackground(Color.red);

        int height = 600;
        int width = 800;
        bgp.setBounds(0, 0, width, height);
        bgp.add(cat);
        bgp.add(fish);

        
        for (DiamondPosition fk : aiList) {
            MyPanel panel = new MyPanel(fk);
            panel.setBackground(Color.gray);
            bgp.add(panel);
        }

        this.add(bgp);
        this.repaint();
        this.setVisible(true);
    }

    private void movePanel(List<DiamondPosition> wayList) throws InterruptedException {

        if (wayList == null || wayList.size() == 0) {
            System.out.println("can't reach the target!");
            return;
        }

        for (int i = wayList.size() - 2; i >= 0; i--) {
            DiamondPosition fk = wayList.get(i);
            // 向上
            long sleepTime = 10;
            while (cat.getY() > fk.getY() * MyPanel.SIZE) {
                cat.setBounds(cat.getX(), cat.getY() - 2, MyPanel.SIZE, MyPanel.SIZE);
                Thread.sleep(sleepTime);
            }

            // 向下
            while (cat.getY() < fk.getY() * MyPanel.SIZE) {
                cat.setBounds(cat.getX(), cat.getY() + 2, MyPanel.SIZE, MyPanel.SIZE);
                Thread.sleep(sleepTime);
            }

            // 向左
            while (cat.getX() > fk.getX() * MyPanel.SIZE) {
                cat.setBounds(cat.getX() - 2, cat.getY(), MyPanel.SIZE, MyPanel.SIZE);
                Thread.sleep(sleepTime);
            }

            // 向右
            while (cat.getX() < fk.getX() * MyPanel.SIZE) {
                cat.setBounds(cat.getX() + 2, cat.getY(), MyPanel.SIZE, MyPanel.SIZE);
                Thread.sleep(sleepTime);
            }

        }
        System.out.println("over! ");
    }

    private static void getAiList() {
        while (aiList.size() < 60) {
            int x = ThreadLocalRandom.current().nextInt(widthLength);
            int y = ThreadLocalRandom.current().nextInt(heightLength);
            DiamondPosition fk = new DiamondPosition(x, y);
            if (aiList.contains(fk)
                    || (cat.getX() == fk.getX() * MyPanel.SIZE && cat.getY() == fk.getY() * MyPanel.SIZE)
                    || (fish.getX() == fk.getX() * MyPanel.SIZE && fish.getY() == fk.getY() * MyPanel.SIZE)) {
                continue;
            }
            aiList.add(fk);
        }
    }

}
