package com.company;


import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

        playSound("Chill-house-music-loop-116-bpm.wav");


        terminal.setCursorVisible(false);

        Monster a = new Monster();


        List<Monster> mss = MonsterGenerator.monsterList();
        MonsterThread monsterthread = new MonsterThread(a, terminal, lock, myHero,mss);
        monsterthread.start();


        for (int i = mss.size() - 1; i >= 0; i--) {
            MonsterThread mth = new MonsterThread(mss.get(i), terminal, lock, myHero, mss);
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

                    Bullet bullet = new Bullet(myHero.getX() + 1, myHero.getY(), terminal, lock, mss);
                    bullet.start();
                    playSound("380_gunshot_single-mike-koenig.wav");
                    Collision cs = new Collision(mss, terminal, bullet);
//                    cs.collision(mss, terminal, bullet);
                    if (bullet.getX() > 100) {
//                        bullet = null;
//                        System.out.println("ok");d
//                    }
//                    System.out.println("hej igen");
                        break;

                    }
                    myHero.drawCharacter();
                    System.out.println(key.getCharacter() + " " + key.getKind());
            }
        }
    }

    public static synchronized void playSound(String file) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream(file));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}