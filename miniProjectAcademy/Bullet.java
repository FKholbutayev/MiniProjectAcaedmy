package com.company;

import com.company.Player;
import com.sun.javafx.scene.traversal.Direction;
import com.googlecode.lanterna.terminal.Terminal;
import jdk.nashorn.internal.ir.IfNode;

import java.security.Key;
import java.util.List;

public class Bullet extends Thread {

    private Monster monster;
    private Terminal terminal;
    private int x;
    private int y;
    private float bulletSpeed = 1.2f;
    private List<Monster> monsters;
    Player hero;
    public Object lock;


    public Bullet(int x, int y, Terminal terminal, Object lock, List<Monster> monsters) {
        this.x = x;
        this.y = y;
        this.terminal = terminal;
        this.lock = lock;
        this.monsters = monsters;
    }

    public Bullet(){
        this.x = x;
        this.y = y;
        this.terminal = terminal;
        this.lock = lock;
    }

    public void move() {
        x++;
    }

    public void run() {
        boolean ok = true;
        while (ok) {
            com.googlecode.lanterna.input.Key key;
            key = terminal.readInput();
            try {
                Thread.sleep(10);
                synchronized (lock) {
                    if (this.x<100) {

                        this.terminal.setCursorVisible(false);
                        this.terminal.moveCursor(this.x, this.y);
                        this.terminal.putCharacter(' ');
                        move();
                        this.terminal.moveCursor(this.x, this.y);
                        for (int i = monsters.size()-1; i>=0; i--) {
                            if ((monsters.get(i).x == x) && (monsters.get(i).y == y)) {
                                System.out.println("walla walla");
                                monsters.get(i).c = ' ';
                                monsters.remove(i);
                                //MonsterGenerator.monsterList();
                                System.out.println("we are here");
                                System.out.println("monster added");
                            }
                        }
                        this.terminal.putCharacter('*');

                    }
                    else {
                        this.terminal.moveCursor(this.x, this.y);
                        this.terminal.putCharacter(' ');
                        break;
                    }


                }


            } catch (InterruptedException e) {
                System.out.println("catched");
            }
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }
}