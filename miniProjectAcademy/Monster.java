package com.company;

import jdk.nashorn.internal.ir.Terminal;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017-06-27.
 */
public class Monster {
    public int x;
    public int y;
    public int speed;
    public char c;

    public Monster() {
        this.x = 80;
        this.y =  0 + (int)(Math.random() * ((40 - 0) + 1));
        this.speed = 50 + (int)(Math.random() * ((150 - 50) + 1));
        this.c = '0';

    }

    public static synchronized void monsterMove(com.googlecode.lanterna.terminal.Terminal terminal) {

        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Monster monster = new Monster();
                    boolean ok = true;
                    while (ok){
//                        terminal.clearScreen();
//                        Monster monster = new Monster();

                        if (monster.x > -1) {
                            monster.x = monster.x - 1;
                            terminal.moveCursor(monster.x, monster.y);
                            terminal.putCharacter(monster.c);
                            terminal.moveCursor(0, 0);

                            Thread.sleep(monster.speed);


                        }
                        else {
                            monster = null;
                            ok = false;
                        }


                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}


