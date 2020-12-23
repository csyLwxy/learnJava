package com.learn.corejava.ch8.pair1;

import com.learn.corejava.ch8.generic.Pair;

/**
 * @author HP
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg {
    /**
     * Gets the minimum and maximum of an array of strings
     * @param a an array of strings
     * @return a pair with the min and max value, or null if a is null or empty
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (String str : a) {
            min = (min.compareTo(str) < 0) ? min : str;
            max = (max.compareTo(str) > 0) ? max : str;
        }
        return new Pair<>(min, max);
    }
}