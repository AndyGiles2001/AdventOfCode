package main.utils;

import java.util.StringJoiner;

public class MutableIntArray {
    
    private int[] arr;
    private int size;
    private boolean initialized;

    public MutableIntArray() {
        size = -1;
        initialized = false;
    }

    public int size() {
        if (!initialized) {
            throw new RuntimeException();
        }

        return size;
    }

    public void initialize(int size) {
        this.size = size;
        arr = new int[size];
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public int get(int i) {
        return arr[i];
    }

    public void incrementAt(int i) {
        arr[i]++;
    }

    public void decrementAt(int i) {
        arr[i]--;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(arr[i]));
        }
        return String.format("[%s]", stringJoiner.toString());
    }
}
