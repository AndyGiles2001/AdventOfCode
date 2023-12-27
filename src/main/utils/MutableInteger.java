package main.utils;

public class MutableInteger {
    
    private int value;

    public MutableInteger() {
        value = 0;
    }

    public MutableInteger(int initialValue) {
        value = initialValue;
    }

    public int get() {
        return value;
    }

    public void set(int newValue) {
        value = newValue;
    }

    public void add(int addend) {
        value += addend;
    }

    public void subtract(int subtrahend) {
        value -= subtrahend;
    }

    public void increment() {
        add(1);
    }

    public void decrement() {
        subtract(1);
    }

    public String toString() {
        return String.valueOf(value);
    }
}
