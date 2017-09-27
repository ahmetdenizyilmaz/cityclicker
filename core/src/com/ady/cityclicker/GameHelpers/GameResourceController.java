package com.ady.cityclicker.GameHelpers;

/**
 * Created by ADY on 8.9.2017.
 */

public class GameResourceController {

    public int worker;
    public long xp = 0;
    public int level = 0;


    public GameResourceController(int worker) {
        this.worker = worker;
    }


    public void gainXP(int xp) {
        this.xp += xp;
        if (this.xp >= Math.pow(level+1f,1.45f)*10   ) {
            this.xp -= Math.pow(level+1f,1.45f)*10;
            level++;
            System.out.println("new level:"+level);
        }
    }
}
