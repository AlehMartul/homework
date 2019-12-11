package ru.javacore;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringOperationTest {
    @DataProvider
    public Object[][] getDataTestGetTwoLines() {
        return new Object[][]{{"AB", "BA", "ABBA"}, {"I", "Q", "IQ"}, {"hell", "o", "hello"}};
    }

    @Test(dataProvider = "getDataTestGetTwoLines")
    public void testGetTwoLines(String str1, String str2, String expected) {
        StringOperation stringOperation = new StringOperation();
        String actual = stringOperation.getTwoLines(str1, str2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetReverseSum() {
        StringOperation stringOperation = new StringOperation();
        String actual = stringOperation.getReverseSum("jorno", "bon ");
        String expected = "bon jorno";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetLength() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getLength("The Offspring");
        int expected = 13;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetTotalLength() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getTotalLength("The", " Offspring");
        int expected = 13;
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {ArithmeticException.class})
    public void testGetDiv() {
        StringOperation stringOperation = new StringOperation();
        int a = 1;
        int b = 0;
        double actual = stringOperation.getDiv(a, b);
        double expected = 0;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetMult() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getMult(4, 7);
        int expected = 28;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetLengthOfArray() {
        StringOperation stringOperation = new StringOperation();
        int[] arr = new int[]{0, 1, 2, 3, 4};
        int actual = stringOperation.getLengthOfArray(arr);
        int expected = 5;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetSquare() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getSquare(11);
        int expected = 121;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetCube() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getCube(4);
        int expected = 64;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetLastNumber() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getLastNumber(23);
        int expected = 3;
        Assert.assertEquals(actual, expected);
    }
}