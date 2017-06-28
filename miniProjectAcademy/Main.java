package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        Monster monster = new Monster();
        Player hero = new Player(0, 20);
        terminal.moveCursor(hero.x, hero.y);
        terminal.putCharacter(hero.c);

        terminal.moveCursor(monster.x, monster.y);
        terminal.putCharacter(monster.c);

//        List<Monster> monsterList = Monster.monsterList();


        Monster.monsterMove(terminal);


        while (true) {
            Key key;
            int i = 0;
            Thread.sleep(60);
                key =terminal.readInput();

                if (key !=null){
                    Monster.monsterMove(terminal);

                    switch (key.getKind()){
                        case ArrowLeft:
                            hero.x = hero.x-1;
                            break;
                        case ArrowDown:
                            hero.y = hero.y+1;
                            break;
                        case ArrowUp:
                            hero.y = hero.y-1;
                            break;
                        case ArrowRight:
                            hero.x = hero.x+1;
                            break;
                        default:

                    }

                }

                terminal.clearScreen();

            terminal.moveCursor(hero.x, hero.y);
            terminal.putCharacter(hero.c);
            terminal.moveCursor(0, 0);
            do {
//                Monster.monsterMove(terminal);
                i++;
            } while (i<7);


        }
    }
}
