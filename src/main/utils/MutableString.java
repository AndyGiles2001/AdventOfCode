package main.utils;

public class MutableString {
    
    private String s;

    public MutableString() {}

    public MutableString(String s) {
        this.s = s;
    }

    public String get() {
        return s;
    }

    public void set(String s) {
        this.s = s;
    }
}
