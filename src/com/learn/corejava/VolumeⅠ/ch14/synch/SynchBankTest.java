package com.learn.corejava.VolumeⅠ.ch14.synch;

/**
 * This program shows how multiple threads can safely access a data structure.
 *
 * @author Cay Horstmann
 * @version 1.30 2004-08-01
 */
public class SynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}

