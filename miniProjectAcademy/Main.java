package com.company;


import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        Object lock = new Object();
        Hero myHero = new Hero(15, 20, 3, terminal, lock);
        //Bullet bullet = new Bullet(myHero.getX()+1, myHero.getY(), terminal, lock);
        //bullet.start();
        System.out.println(terminal.getTerminalSize());

        terminal.moveCursor(myHero.getX(), myHero.getY());
        terminal.putCharacter('O');


        terminal.setCursorVisible(false);

        Monster a = new Monster();
        MonsterThread monsterthread = new MonsterThread(a, terminal, lock);
        monsterthread.start();
        List<Monster> mss = MonsterGenerator.monsterList();




        for (int i = mss.size()-1 ;i>=0; i--){
            MonsterThread mth = new MonsterThread(mss.get(i), terminal, lock);
            mth.start();
        }

        while (true) {


            boolean running = true;


            Key key;
            do {
                Thread.sleep(10L);
                key = terminal.readInput();
            } while (key == null);

            System.out.println(key.getCharacter() + " " + key.getKind());
            switch (key.getKind()) {
                case ArrowDown:
                    myHero.moveDown();
                    break;
                case ArrowUp:
                    myHero.moveUp();
                    break;
                case ArrowLeft:
                    myHero.moveLeft();

                    break;
                case ArrowRight:
                    myHero.moveRight();
                    break;

                case NormalKey:
                    Bullet bullet = new Bullet(myHero.getX()+1, myHero.getY(), terminal, lock, mss);
                    bullet.start();
                    Collision cs = new Collision(mss, terminal, bullet);
                    cs.collision(mss, terminal, bullet);
//                    if (bullet.getX()>100){
//                        bullet = null;
//                        System.out.println("ok");
//                    }
//                    System.out.println("hej igen");
                    break;

            }
            myHero.drawCharacter();
            System.out.println(key.getCharacter() + " " + key.getKind());
        }
    }
}