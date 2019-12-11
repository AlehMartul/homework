package ru.javacore;

public class StringOperation {
    public String getTwoLines(String line1, String line2) {
        return line1.concat(line2);
    }

    public String getReverseSum(String line1, String line2) {
        return line2.concat(line1);
    }

    public int getLength(String line) {
        return line.length();
    }

    public int getTotalLength(String line1, String line2) {
        return line1.length() + line2.length();
    }

    public int getMult(int a, int b) {
        return a * b;
    }

    public double getDiv(int a, int b) {
        return a / b;
    }

    public int getLengthOfArray(int[] array) {
        return array.length;
    }

    public int getSquare(int a) {
        return a * a;
    }

    public int getCube(int a) {
        return a * a * a;
    }

    public int getLastNumber(int a) {
        return a % 10;
    }

}
