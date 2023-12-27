package main.utils;

public class ComplexNumber {
    
    private int real;
    private int imaginary;

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber() {
        real = 0;
        imaginary = 0;
    }

    public int getReal() {
        return real;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void addReal(int realAddend) {
        real += realAddend;
    }

    public void subtractReal(int realSubtrahend) {
        real -= realSubtrahend;
    }

    public void addImaginary(int imaginaryAddend) {
        imaginary += imaginaryAddend;
    }

    public void subtractImaginary(int imaginarySubtrahend) {
        imaginary -= imaginarySubtrahend;
    }

    public void addComplex(ComplexNumber complexAddend) {
        real += complexAddend.real;
        imaginary += complexAddend.imaginary;
    }

    public void subtractComplex(ComplexNumber complexSubtrahend) {
        real -= complexSubtrahend.real;
        imaginary -= complexSubtrahend.imaginary;
    }

    public void multiply(int realMultiplier) {
        real *= realMultiplier;
        imaginary *= realMultiplier;
    }

    public void multiply(ComplexNumber complexMultiplier) {
        int newReal = real * complexMultiplier.real - imaginary * complexMultiplier.imaginary;
        imaginary = real * complexMultiplier.imaginary + imaginary * complexMultiplier.real;
        real = newReal;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.valueOf(real));
        if (imaginary < 0) {
            stringBuilder.append(" - ");
        } else {
            stringBuilder.append(" + ");
        }
        stringBuilder.append(String.format("%di", Math.abs(imaginary)));

        return stringBuilder.toString();
    }
}
