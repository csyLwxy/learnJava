package com.learn.ch4;

import java.time.LocalDate;
import java.util.Random;

public class Employee {
    private static int nextId = 1;

    private String name = "";
    private double salary;
    private int id;
    private LocalDate hireDay;

    // static initialization block
    static {
        Random generator = new Random();
        // set nextId to a random number between 0 and 9999
        nextId = generator.nextInt(10000);
    }

    // object initialization block
    {
        id = nextId;
        nextId++;
    }

    public Employee() {
        // name initialized to "
        // salary not explicitly set -- initialized to 0
        // id initialized in initialization block
    }

    public Employee(double s) {
        // calls the Employee(String, double) constructor
        this("Employee #" + nextId, s);
    }

    public Employee(String n, double s) {
        name = n;
        salary = s;
        id = 0;
    }

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay =LocalDate.of(year, month, day);
    }

    public void setId() {
        id = nextId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }
}
