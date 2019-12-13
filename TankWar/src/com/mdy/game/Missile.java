package com.mdy.game;

import com.mdy.game.enums.Mode;
import com.mdy.game.interfaces.Movable;
import com.mdy.game.enums.Direction;

import javax.swing.*;

public class Missile extends MyImage implements Movable {
    private int direction;
    private final static int DAMAGE = 10;
    //子弹的长宽
    final static int BULLET_WIDTH = 10;
    final static int BULLET_HEIGHT = 10;
    private int id;

    Missile(int x, int y, int direction, int id) {
        super(x, y);
        this.height = 10;
        this.width = 10;
        this.direction = direction;
        this.id = id;
    }
    /**
     * @return 子弹是否碰到物体
     */
    public boolean isMeet() {
        // 判断是否撞墙
        for (Wall wall : Game.walls.values()) {
            if (wall.isIntersects(this)) {
                if (wall.id == Game.WALL) {
                    Game.map[wall.coord.y][wall.coord.x] = Game.BLANK;
                    Game.walls.remove(wall.hashCode());
                }
                return true;
            }
        }
        // 判断是否与坦克碰撞
        for (Tank tank : Game.tanks.values()) {
            if (tank.isIntersects(this)) {
                //子弹的伤害
                if (id != tank.id) {
                    tank.hp -= DAMAGE /10;
                }
                if (tank.hp <= 0) {
                    //如果该坦克不属于玩家控制的话就不进行下一步的处理
                    if (tank.id >= Game.PLAY_1) {
                        tank.flag = false;
                        if (Game.mode == Mode.Single) {
                            if (tank.equals(Game.tanks.get(tank.hashCode()))) {
                                Game.shutDown();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, String.valueOf(tank.id == Game.PLAY_1 ? "p2 win!!" : "p2 win!!"));
                            Game.shutDown();
                        }
                    }
                    Game.map[tank.coord.y][tank.coord.x] = Game.BLANK;
                    tank.flag = false;
                    Game.tanks.remove(tank.hashCode());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @return 是否碰撞到物体
     */
    public boolean move() {
        switch (direction) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        return isMeet();
    }
}
