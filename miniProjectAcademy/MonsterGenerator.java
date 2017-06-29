package com.company;

import java.util.ArrayList;
import java.util.List;


public class MonsterGenerator {

    public static List<Monster> monsterList(){
        List<Monster> monsters = new ArrayList<>();

        for (int i = 0; i<=100; i++){
            monsters.add(new Monster());
        }


        return monsters;

    }
}
