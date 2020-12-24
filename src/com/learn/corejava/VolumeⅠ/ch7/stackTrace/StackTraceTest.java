package com.learn.corejava.VolumeⅠ.ch7.stackTrace;

import java.util.Scanner;

/**
 * A program that displays a trace feature of a recursive method call
 * @author HP
 */
public class StackTraceTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = in.nextInt();
        factorial(n);
    }

    /**
     * Computes the factorial of a number
     */
    public static int factorial(int n) {
        System.out.println("factorial(" + n + ")");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames) {
            System.out.println(f);
        }
        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }
        System.out.println("return " + r);
        return r;
    }
}
