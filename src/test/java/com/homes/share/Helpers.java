package com.homes.share;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Helpers {

    public void registerLog(String log) {
        System.out.println("TEST LOG: " + log);
    }

    public Set<Integer> getRandomIndexes(int numbersNeeded, int max) {

        Random random = new Random();

        Set<Integer> generated = new HashSet<Integer>();
        while (generated.size() < numbersNeeded) {
            Integer next = random.nextInt(max);
            generated.add(next);
        }

        return generated;
    }
}

