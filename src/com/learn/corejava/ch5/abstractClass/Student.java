package com.learn.corejava.ch5.abstractClass;

/**
 * @author HP
 */
public class Student extends Person {
    private String major;

    /**
     * @param name the student's name
     * @param major the student's major
     */
    public Student(String name, String major) {
        // pass nme to superClass constructor
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }
}
