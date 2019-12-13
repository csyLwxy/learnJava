package com.mdy.game;

/**
 * 游戏的各种方块的抽象
 */
public class Wall extends MyImage{

    //识别为那种方块的id
    int id;

    public Wall(Coord coord, int id){
        super(coord);
        this.id = id;
    }
}
