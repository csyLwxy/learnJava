package com.learn.corejava.VolumeⅠ.ch8.pair2;

import com.learn.corejava.VolumeⅠ.ch8.generic.Pair;

import java.time.LocalDate;

/**
 * @author HP
 */
public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
             LocalDate.of(1906, 12, 9), // G. Hopper
             LocalDate.of(1815, 12, 10), // A. Lovelace
             LocalDate.of(1903, 12, 3), // J. von Neumann
             LocalDate.of(1910, 6, 22), // K. Zuse
         };
        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg {
    /**
     * Gets the minimum and maximum of an array of objects of type T.
     * @param a an array of objects of type T
     * @return a pair with the min and max value, or null if a is null or empty
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (T t : a) {
            if (min.compareTo(t) > 0) {
                min = t;
            }
            if (max.compareTo(t) < 0) {
                max = t;
            }
        }
        return new Pair<>(min, max);
    }
}