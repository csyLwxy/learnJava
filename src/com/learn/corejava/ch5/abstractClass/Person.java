package com.learn.corejava.ch5.abstractClass;

/**
 * @author HP
 */
public abstract class Person {
    /**
     * 对对象的描述
     * @return 对对象的描述
     */
    public abstract String getDescription();
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
