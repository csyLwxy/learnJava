package com.learn.corejava.VolumeⅠ.ch8.pair3;

import com.learn.corejava.VolumeⅠ.ch4.Employee;
import com.learn.corejava.VolumeⅠ.ch5.inheritance.Manager;
import com.learn.corejava.VolumeⅠ.ch8.generic.Pair;

/**
 * @author HP
 */
public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = { ceo, cfo };

        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName()
                + ", second: " + result.getSecond().getName());
        maxminBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName()
                + ", second: " + result.getSecond().getName());
    }

    private static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    private static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (Manager m : a) {
            if (min.getBonus() > m.getBonus()) {
                min = m;
            }
            if (max.getBonus() < m.getBonus()) {
                max = m;
            }
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    private static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        PairAlg.swapHelp(result);
    }
}

class PairAlg {
    protected static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    protected static void swap(Pair<?> p) {
        swapHelp(p);
    }

    protected static <T> void swapHelp(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}