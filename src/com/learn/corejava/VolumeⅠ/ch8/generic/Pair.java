package com.learn.corejava.VolumeⅠ.ch8.generic;

/**
 * @author HP
 * @param <T> 类型变量
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(T second) { this.second = second; }
}