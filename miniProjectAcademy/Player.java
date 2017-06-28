package com.company;

/**
 * Created by Administrator on 2017-06-21.
 */

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import java.nio.charset.Charset;

public class Player {
    public int x;
    public int y;
    public char c;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.c = '\u0058';
    }

}
