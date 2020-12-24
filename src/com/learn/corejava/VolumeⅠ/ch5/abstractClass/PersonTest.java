package com.learn.corejava.VolumeⅠ.ch5.abstractClass;

/**
 * This program demonstrates abstract classes
 * @author HP
 */
public class PersonTest {
    public static void main(String[] args) {
        Person student = new Student("Maria Morris", "CS");

        System.out.println(student.getDescription());
    }
}
