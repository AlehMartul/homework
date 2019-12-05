public class StringOperation {
    public String getSum(String line1, String line2) {
        return line1 + line2;
    }

    public String getReverseSum(String line1, String line2, String line3) {
        return line3 + line2 + line1;
    }

    public int getLength(String line) {
        return line.length();
    }

    public int getTotalLength(String line1, String line2) {
        return line1.length() + line2.length();
    }

    public int getAverageLength(String line1, String line2) {
        int num = 2;
        return (line1.length() + line2.length()) / num;
    }

    public boolean isFirstLonger(String line1, String line2) {
        return (line1.length() > line2.length() ? true : false);
    }

    public boolean isEqualsLength(String line1, String line2) {
        return (line1.length() == line2.length() ? true : false);
    }

    public int findA(String line) {
        return line.indexOf('a');
    }
}
