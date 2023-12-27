package main.utils;

public class MutableBoolean {
    
    private boolean value;

    public MutableBoolean() {
        value = false;
    }

    public MutableBoolean(boolean initialValue) {
        value = initialValue;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean newValue) {
        value = newValue;
    }

    public void set() {
        set(true);
    }

    public void clear() {
        set(false);
    }
}
