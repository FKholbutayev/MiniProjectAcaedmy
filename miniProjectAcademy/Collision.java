package com.company;


import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

/**
 * Created by Administrator on 2017-06-28.
 */
public class Collision {
    private Terminal terminal;
    private Bullet bullet;
    private List<Monster> mss;

    public Collision(List<Monster> mss, Terminal terminal, Bullet bullet) {
        this.mss = mss;
        this.terminal = terminal;
        this.bullet = bullet;
    }

    public void collision(List<Monster> mss, Terminal terminal, Bullet bullet){
        for (int i = mss.size()-1; i>=0; i--){
            if (mss.get(i).x == bullet.getX() && mss.get(i).y == bullet.getY()){
                System.out.println("Collosion in collision");
            }
        }
    }

}
