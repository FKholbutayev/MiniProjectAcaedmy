package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-06-28.
 */
public class MonsterGenerator {

    public static List<Monster> monsterList(){
        List<Monster> monsters = new ArrayList<>();

        for (int i = 0; i<=10; i++){
            monsters.add(new Monster());
        }

        return monsters;

    }
}
