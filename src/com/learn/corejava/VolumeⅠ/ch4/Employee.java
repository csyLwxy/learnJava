package com.learn.corejava.VolumeⅠ.ch4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/**
 * @author HP
 */
public class Employee implements Comparable<Employee>, Cloneable, Serializable {
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

    public Employee(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object otherObject) {
        // a quick test to see if the objects are identical
        if (this == otherObject) {
            return true;
        }

        // must return false if the explicit parameter is null
        if (otherObject == null) {
            return false;
        }

        // if the classes don't match they can't be equal
        if (getClass() != otherObject.getClass()) {
            return false;
        }

        // now we know otherObject is a non-null Employee
        Employee other = (Employee) otherObject;

        // test whether the fields have identical value
        return Objects.equals(name, other.name)
                && salary == other.salary
                && id == other.id
                && Objects.equals(hireDay, other.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, id, hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireday=" + hireDay + "]";
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary, o.salary);
    }
}
