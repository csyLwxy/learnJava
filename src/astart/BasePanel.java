package astart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class BasePanel extends JFrame {
    private static final long serialVersionUID = 1L;
    private static int beginX = 0;
    private static int beginY = 0;
    private static int frameWidth = 815;
    private static int frameHeight = 635;
    private static int width = 800;
    private static int height = 600;
    public static int widthLength = 16;
    public static int heightLength = 12;
    public static BackGroundPanel bgp = new BackGroundPanel();

    public static MyPanel cat = new MyPanel(0, 0);
    public static MyPanel fish = new MyPanel(ThreadLocalRandom.current().nextInt(widthLength),
            ThreadLocalRandom.current().nextInt(heightLength));
    private final long sleepTime = 10;

    public static List<DiamondPosition> zhangaiList = new ArrayList<>();
    public static List<DiamondPosition> closedList = new ArrayList<>(); 
    public static List<DiamondPosition> openList = new ArrayList<>(); 

    public BasePanel() {
        
        Dimension dimension = CommonUtil.getScreenSize();
        
        beginX = (int) (dimension.getWidth() / 2 - 400);
        beginY = (int) (dimension.getHeight() / 2 - 300);
        this.setBounds(beginX, beginY, frameWidth, frameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        
        cat.setBackground(Color.green);
        
        fish.setBackground(Color.red);

        bgp.setBounds(0, 0, width, height);
        bgp.add(cat);
        bgp.add(fish);

        
        for (DiamondPosition fk : zhangaiList) {
            MyPanel panel = new MyPanel(fk);
            panel.setBackground(Color.gray);
            bgp.add(panel);
        }

        this.add(bgp);
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        getZhangAiList();
        BasePanel bp = new BasePanel();
        AutoFindWay afw = new AutoFindWay();
        List<DiamondPosition> wayList = afw.getWayLine(cat, fish);
        bp.movePanel(wayList);
    }

    public void movePanel(List<DiamondPosition> wayList) throws InterruptedException {

        if (wayList == null || wayList.size() == 0) {
            System.out.println("can't reach the target!");
            return;
        }

        for (int i = wayList.size() - 2; i >= 0; i--) {
            DiamondPosition fk = wayList.get(i);
            // 向上
            while (cat.getY() > fk.getY() * MyPanel.size) {
                cat.setBounds(cat.getX(), cat.getY() - 2, MyPanel.size, MyPanel.size);
                Thread.sleep(sleepTime);
            }

            // 向下
            while (cat.getY() < fk.getY() * MyPanel.size) {
                cat.setBounds(cat.getX(), cat.getY() + 2, MyPanel.size, MyPanel.size);
                Thread.sleep(sleepTime);
            }

            // 向左
            while (cat.getX() > fk.getX() * MyPanel.size) {
                cat.setBounds(cat.getX() - 2, cat.getY(), MyPanel.size, MyPanel.size);
                Thread.sleep(sleepTime);
            }

            // 向右
            while (cat.getX() < fk.getX() * MyPanel.size) {
                cat.setBounds(cat.getX() + 2, cat.getY(), MyPanel.size, MyPanel.size);
                Thread.sleep(sleepTime);
            }

        }
        System.out.println("over! ");
    }

    public static void getZhangAiList() {

        while (zhangaiList.size() < 60) {

            int x = ThreadLocalRandom.current().nextInt(widthLength);
            int y = ThreadLocalRandom.current().nextInt(heightLength);
            DiamondPosition fk = new DiamondPosition(x, y);
            
            if (zhangaiList.contains(fk)
                    || (cat.getX() == fk.getX() * MyPanel.size && cat.getY() == fk.getY() * MyPanel.size)
                    || (fish.getX() == fk.getX() * MyPanel.size && fish.getY() == fk.getY() * MyPanel.size)) {
                continue;
            }
            zhangaiList.add(fk);
        }
    }

}
