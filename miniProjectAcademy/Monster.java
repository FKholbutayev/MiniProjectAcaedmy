package com.company;

//import jdk.nashorn.internal.ir.Terminal;
//

import com.googlecode.lanterna.terminal.Terminal;

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
    public com.googlecode.lanterna.terminal.Terminal terminal;
    public Bullet bullet;
    public Object lock;
    public Hero myHero;

    public Monster() {
        this.x = 80;
        this.y = 0 + (int) (Math.random() * ((30 - 0) + 1));
        this.speed = 50 + (int) (Math.random() * ((150 - 50) + 1));
        this.c = 'X';

    }
}

class MonsterThread extends Thread {
    private Monster monster;
    private Object lock;
    private com.googlecode.lanterna.terminal.Terminal terminal;
    private Hero myHero;
    private List<Monster> monsters;
    private Bullet bullet;


    public MonsterThread(Monster monster, Terminal terminal, Object lock, Hero myHero,List<Monster> monsters) {
        this.monster = monster;
        this.lock = lock;
        this.terminal = terminal;
        this.myHero = myHero;
        this.monsters = monsters;
//        this.bullet = bullet;
    }
//
//    public MonsterThread(Monster monster, Terminal terminal, Object lock, Hero myHero,List<Monster> monsters, Bullet bullet) {
//        this.monster = monster;
//        this.lock = lock;
//        this.terminal = terminal;
//        this.myHero = myHero;
//        this.monsters = monsters;
//        this.bullet = bullet;
//    }

    @Override
    public void run() {
        boolean ok = true;
        while (ok) {
            try {


                if (monster.x > -1) {
                    monster.x = monster.x - 1;
                    synchronized (lock) {
                        terminal.moveCursor(monster.x, monster.y);
                        terminal.putCharacter(monster.c);
                        terminal.putCharacter(' ');
                        terminal.moveCursor(0, 0);

                    }
                    for (int i = monsters.size()-1; i>=0; i--) {
                        if ((monsters.get(i).x == myHero.getX()) && (monsters.get(i).y == myHero.getY())) {
                            System.out.println("monsterrrrrr");
                            monsters.get(i).c = ' ';
                            monsters.remove(i);
                            printText(40, 15, "GAME IS OVER", terminal);
                            Thread.sleep(3000);
                            terminal.exitPrivateMode();
                        }
                    }

                    Thread.sleep(monster.speed);


                } else {
                    terminal.moveCursor(monster.x, monster.y);
                    terminal.putCharacter(' ');
                    monster = null;
                    ok = false;
//                    for (int i = monsters.size()-1; i>=0; i--) {
//                        if (monsters.get(i).x == bullet.getX() && monsters.get(i).y == bullet.getY()) {
//                            System.out.println("walla walla");
//                            monsters.get(i).c = ' ';
//                            monsters.remove(i);
//                            //MonsterGenerator.monsterList();
//                            System.out.println("we are here");
//                            monsters.set(i, new Monster());
//
//
//                            System.out.println("monster added");
//                        }
                    }
                //}
            } catch (InterruptedException e) {}
        }
    }
    private static void printText(int x, int y, String message, Terminal
            terminal) {
        for (int i=0;i<message.length();i++)
        {
            terminal.moveCursor(x++, y);
            terminal.putCharacter(message.charAt(i));
        }
    }
}



//   public static synchronized void monsterMove(Monster monster, com.googlecode.lanterna.terminal.Terminal terminal) {
//
//        new Thread(new Runnable() {
//            // The wrapper thread is unnecessary, unless it blocks on the
//            // Clip finishing; see comments.
//            public void run() {
//                try {
////                    Monster monster = new Monster();
//                    boolean ok = true;
//                    while (ok){
////                        terminal.clearScreen();
////                        Monster monster = new Monster();
//
//                        if (monster.x > -1) {
//                            monster.x = monster.x - 1;
//                            terminal.moveCursor(monster.x, monster.y);
//                            terminal.putCharacter(monster.c);
//                            terminal.moveCursor(0, 0);
//
//                            Thread.sleep(monster.speed);
//
//
//                        }
//                        else {
//                            monster = null;
//                            ok = false;
//                        }
//
//
//                    }
//                } catch (Exception e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//        }).start();
//    }




