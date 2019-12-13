package com.mdy.game;

import com.mdy.game.enums.Mode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 坦克游戏界面
 */
public class Game extends JPanel {
    private static Image[] array = new Image[23];
    private Image offScreenImage;
    //玩家1坦克的hashcode
    static int P1_TAG;
    //玩家2
    static int P2_TAG;

    // 初始化array
    static {
        array[0] = new ImageIcon("./resources/img/walls.gif").getImage();
        array[1] = new ImageIcon("./resources/img/steels.gif").getImage();
        array[2] = new ImageIcon("./resources/img/enemy1D.gif").getImage();
        array[3] = new ImageIcon("./resources/img/enemy1L.gif").getImage();
        array[4] = new ImageIcon("./resources/img/enemy1R.gif").getImage();
        array[5] = new ImageIcon("./resources/img/enemy1U.gif").getImage();
        array[6] = new ImageIcon("./resources/img/enemy2D.gif").getImage();
        array[7] = new ImageIcon("./resources/img/enemy2L.gif").getImage();
        array[8] = new ImageIcon("./resources/img/enemy2R.gif").getImage();
        array[9] = new ImageIcon("./resources/img/enemy2U.gif").getImage();
        array[10] = new ImageIcon("./resources/img/enemy3D.gif").getImage();
        array[11] = new ImageIcon("./resources/img/enemy3L.gif").getImage();
        array[12] = new ImageIcon("./resources/img/enemy3R.gif").getImage();
        array[13] = new ImageIcon("./resources/img/enemy3U.gif").getImage();
        array[14] = new ImageIcon("./resources/img/p1tankD.gif").getImage();
        array[15] = new ImageIcon("./resources/img/p1tankL.gif").getImage();
        array[16] = new ImageIcon("./resources/img/p1tankR.gif").getImage();
        array[17] = new ImageIcon("./resources/img/p1tankU.gif").getImage();
        array[18] = new ImageIcon("./resources/img/p2tankD.gif").getImage();
        array[19] = new ImageIcon("./resources/img/p2tankL.gif").getImage();
        array[20] = new ImageIcon("./resources/img/p2tankR.gif").getImage();
        array[21] = new ImageIcon("./resources/img/p2tankU.gif").getImage();
        array[22] = new ImageIcon("./resources/img/tankmissile.gif").getImage();
    }


    static Mode mode;

    public static boolean isAlive = true;

    //坦克的移动区域
    private final static int SCREEN_WIDTH = 900;
    private final static int SCREEN_HEIGHT = 600;
    //一般图像的大小
    static final int DEFAULT_WIFTH = 40;
    static final int DEFAULT_HEIGHT = 40;
    //坦克的血量和弹药数
    static final int HP = DEFAULT_WIFTH;
    static final int MP = DEFAULT_WIFTH;
    //坦克的移动
    static final int UP = 3;
    static final int DOWN = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    //map上图像的标志
    final static int BLANK = -1;
    final static int WALLS = -2;
    final static int STEELS = -3;
    //图像的标志
    final static int WALL = 0;
    final static int STEEL = 1;
    final static int ENEMY_1 = 0;
    final static int ENEMY_2 = 4;
    final static int ENEMY_3 = 8;
    //玩家控制的坦克的编号（图像数组中的编号）
    final static int PLAY_1 = 12;
    final static int PLAY_2 = 16;
    //  final static int MISSILE = 22;

    //地图，储存除了子弹以外的东西
    //注意当使用Coord的x和y的时候是map[y][x]
    volatile static int[][] map;

    volatile static ConcurrentHashMap<Integer, Tank> tanks = new ConcurrentHashMap<>();

    volatile static ConcurrentHashMap<Integer, Wall> walls = new ConcurrentHashMap<>();

    volatile static ArrayList<Missile> missile = new ArrayList<>();


    /**
     * 初始化敌方AI坦克
     */
    private void initETank() {
        Coord coord = randomCoord();
        Tank tank = new Tank(coord, DOWN, ENEMY_1);
        map[coord.y][coord.x] = tank.hashCode();
        tanks.put(tank.hashCode(), tank);
        coord = randomCoord();
        tank = new Tank(coord, DOWN, ENEMY_2);
        map[coord.y][coord.x] = tank.hashCode();
        tanks.put(tank.hashCode(), tank);
        coord = randomCoord();
        tank = new Tank(coord, DOWN, ENEMY_3);
        map[coord.y][coord.x] = tank.hashCode();
        tanks.put(tank.hashCode(), tank);
    }

    /**
     * 初始化玩家的坦克
     */
    private void initTank(Mode mode) {
        Coord coord = randomCoord();
        Tank p1 = new Tank(coord, DOWN, PLAY_1);
        P1_TAG = p1.hashCode();
        map[coord.y][coord.x] = p1.hashCode();
        tanks.put(p1.hashCode(), p1);
        //双人模式
        if (mode == Mode.Double) {
            coord = randomCoord();
            Tank p2 = new Tank(coord, DOWN, PLAY_2);
            P2_TAG = p2.hashCode();
            map[coord.y][coord.x] = p2.hashCode();
            tanks.put(p2.hashCode(), p2);
        } else {
            initETank();
        }
    }

    /**
     * 初始化地图
     */
    private void initMap() {

        int x = SCREEN_WIDTH / Game.DEFAULT_WIFTH;
        int y = SCREEN_HEIGHT / Game.DEFAULT_HEIGHT - 1;

        map = new int[y][x];

        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < x; ++j) {
                if (i == 0 || i == y - 1 || j == 0 || j == x - 1) {
                    map[i][j] = STEELS;
                    Wall wall = new Wall(new Coord(j, i), STEEL);
                    walls.put(wall.hashCode(), wall);
                } else {
                    map[i][j] = BLANK;
                }
            }
        }
        //随机产生障碍物
        for (int i = 0; i < x * y / 8; ++i) {
            //Coord的y对应数组的行
            Coord c = randomCoord();
            map[c.y][c.x] = WALLS;
            Wall wall = new Wall(c, WALL);
            walls.put(wall.hashCode(), wall);
        }
    }

    /**
     * 随机坦克的坐标
     */
    private Coord randomCoord() {
        Random random = new Random(System.currentTimeMillis());
        int x, y;
        do {
            y = random.nextInt(map.length);
            x = random.nextInt(map[0].length);
        } while (map[y][x] != BLANK);
        return new Coord(x, y);
    }

    public Game(Mode mode) {
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        setLayout(null);
        Game.mode = mode;
        initMap();
        initTank(mode);
        addKeyListener(new KeyBoardListener());
        isAlive = true;
        new Thread(new MissileMove()).start();
        new Thread(new Draw()).start();
    }


    /**
     * 图像重绘线程
     */
    class Draw implements Runnable {
        @Override
        public void run() {
            while (isAlive) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 子弹移动的线程
     */
    class MissileMove implements Runnable {
        @Override
        public void run() {
            while (isAlive) {
                synchronized ("missile") {
                    for (int i = missile.size() - 1; i >= 0; --i) {
                        if (missile.get(i).move() && isAlive) {
                            missile.remove(i);
                        }
                    }
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 绘制坦克和血条蓝条
     */
    private void paintTank(Graphics2D g2, Tank tank) {
        //血条和蓝条的高度
        int h = 5;
        g2.drawImage(array[2 + tank._direction + tank.id], tank.x, tank.y, DEFAULT_WIFTH, DEFAULT_HEIGHT, null);
        g2.setColor(Color.RED);
        g2.draw3DRect(tank.x, tank.y + 1, HP, h, true);
        g2.fill3DRect(tank.x, tank.y + 1, tank.hp, h, true);
        g2.setColor(Color.BLUE);
        g2.draw3DRect(tank.x, tank.y + 1 + h, MP, h, true);
        g2.fill3DRect(tank.x, tank.y + 1 + h, tank.mp, h, true);
    }

    /**
     * 重绘函数
     */
    @Override
    synchronized public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        //绘画墙体
        for (Wall wall : walls.values()) {
            g2.drawImage(array[wall.id], wall.x, wall.y, DEFAULT_WIFTH, DEFAULT_HEIGHT, null);
        }

        //绘制坦克
        for (Tank tank : tanks.values()) {
            paintTank(g2, tank);
        }

        //子弹绘画
        for (Missile m : missile) {
            g2.drawImage(array[22], m.x, m.y, m.width, m.height, null);
        }
    }

    /**
     * 缓存绘图
     */
    @Override
    synchronized public void update(Graphics g) {
        super.update(g);
        if (offScreenImage == null) {
            offScreenImage = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        }
        Graphics goffscrenn = offScreenImage.getGraphics();    //设置一个内存画笔颜色为前景图片颜色
        Color c = goffscrenn.getColor();    //还是先保存前景颜色
        goffscrenn.setColor(Color.BLACK);    //设置内存画笔颜色为绿色
        goffscrenn.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);    //画成图片，大小为游戏大小
        goffscrenn.setColor(c);    //还原颜色
        g.drawImage(offScreenImage, 0, 0, null);    //在界面画出保存的图片
        paint(goffscrenn);    //把内存画笔调用给paint
    }

    /**
     * 监听按键
     */
    private static class KeyBoardListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            //区分两种不同的按键
            //ASDWG为P2的按键
            //上下左右+SHITF为P1按键
            if (key < 65) {
                if (key != KeyEvent.VK_SPACE && tanks.get(P1_TAG) != null) {
                    tanks.get(P1_TAG).key = key;
                    tanks.get(P1_TAG).move = true;
                }
                if (key == KeyEvent.VK_ESCAPE) {
                    shutDown();
                }
            } else {
                if (key != KeyEvent.VK_G && tanks.get(P2_TAG) != null) {
                    switch (key) {
                        case KeyEvent.VK_W:
                            key = KeyEvent.VK_UP;
                            break;
                        case KeyEvent.VK_A:
                            key = KeyEvent.VK_LEFT;
                            break;
                        case KeyEvent.VK_S:
                            key = KeyEvent.VK_DOWN;
                            break;
                        case KeyEvent.VK_D:
                            key = KeyEvent.VK_RIGHT;
                            break;
                        default:
                            break;
                    }
                    tanks.get(P2_TAG).key = key;
                    tanks.get(P2_TAG).move = true;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            int key = e.getKeyCode();
            if (key < 65) {
                if (tanks.get(P1_TAG) != null) {
                    if (key != KeyEvent.VK_SPACE && key == tanks.get(P1_TAG).key) {
                        tanks.get(P1_TAG).move = false;
                    } else {
                        tanks.get(P1_TAG).getKey(key);
                    }
                }
            } else {
                switch (key) {
                    case KeyEvent.VK_W:
                        key = KeyEvent.VK_UP;
                        break;
                    case KeyEvent.VK_A:
                        key = KeyEvent.VK_LEFT;
                        break;
                    case KeyEvent.VK_S:
                        key = KeyEvent.VK_DOWN;
                        break;
                    case KeyEvent.VK_D:
                        key = KeyEvent.VK_RIGHT;
                        break;
                    case KeyEvent.VK_G:
                        key = KeyEvent.VK_SPACE;
                        break;
                    default:
                        break;
                }
                if (null != tanks.get(P2_TAG)) {
                    if (key != KeyEvent.VK_SPACE && key == tanks.get(P2_TAG).key) {
                        tanks.get(P2_TAG).move = false;
                    } else {
                        tanks.get(P2_TAG).getKey(key);
                    }
                }
            }
        }
    }

    static synchronized void shutDown() {
        Game.isAlive = false;
        //停止AI
        for (Tank tank : Game.tanks.values()) {
            tank.flag = false;
            tank.executorService.shutdown();
        }
        Game.tanks.clear();
        Game.walls.clear();
        Game.missile.clear();
    }
}
