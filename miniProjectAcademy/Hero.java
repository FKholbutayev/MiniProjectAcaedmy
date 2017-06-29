package com.company;


import com.googlecode.lanterna.terminal.Terminal;

public class Hero {
    private int x;
    private int y;
    private int life;
    Terminal terminal;
    public Object lock;


    public Hero( int x, int y, int life, Terminal terminal, Object lock){
            this.x = x;
            this.y = y;
            this.life = life;
            this.terminal = terminal;
            this.lock = lock;
        }


    public void drawCharacter() {
        this.terminal.moveCursor(this.x, this.y);
        this.terminal.putCharacter('O');

    }


    public void moveUp() {
        synchronized (lock) {
            this.terminal.moveCursor(this.x, this.y);
            if (this.y <= 0) {
                System.out.println("upper border control");

            } else {
                this.terminal.putCharacter(' ');
                this.y--;
                this.terminal.moveCursor(this.x, this.y);
                this.terminal.putCharacter('O');
            }
        }


    }

    public void moveDown() {
        synchronized (lock) {
            this.terminal.moveCursor(this.x, this.y);
            if (this.y >= 30) {
                System.out.println("down border control");

            } else {
                this.terminal.putCharacter(' ');
                this.y++;
                this.terminal.moveCursor(this.x, this.y);
                this.terminal.putCharacter('O');
            }
        }


    }

    public void moveRight() {
        synchronized (lock) {
            this.terminal.moveCursor(this.x, this.y);
            if (this.x >= 100) {
                System.out.println("right border control");


            } else {
                this.terminal.putCharacter(' ');
                this.x++;
                this.terminal.moveCursor(this.x, this.y);
                this.terminal.putCharacter('O');
            }
        }


    }

    public void moveLeft() {
        synchronized (lock) {
            this.terminal.moveCursor(this.x, this.y);
            if (this.x <= 0) {
                System.out.println("left border control");


            } else {
                this.terminal.putCharacter(' ');
                this.x--;
                this.terminal.moveCursor(this.x, this.y);
                this.terminal.putCharacter('O');
            }
        }


    }



    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return this.life;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
