package com.company;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017-06-27.
 */
public class mainWithWhileLoop {
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

//        monsterMove(monster, terminal);

        while (true) {
            Key key;
            int i = 0;
            do{
                Thread.sleep(50);
                key =terminal.readInput();
                if (key !=null){
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
                if (monster.x > -1) {
                    monster.x = monster.x - 1;
                    terminal.moveCursor(monster.x, monster.y);
                    terminal.putCharacter(monster.c);
                    i++;
                }
                else
                    monster.c =' ';
                terminal.moveCursor(hero.x, hero.y);
                terminal.putCharacter(hero.c);
                terminal.moveCursor(0, 0);


            }
            while(i < 10);


//
//
//
//            }

//        Monster monster = new Monster(40, 50, 20, '\u0048');

            // write your code here

        }
    }
}
