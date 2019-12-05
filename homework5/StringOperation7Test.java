import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringOperation7Test {

    @Test()
    public void testFindA() {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.findA("12a1");
        int expected = 2;
        Assert.assertEquals(actual, expected);
    }

    @Test()
    public void testGetAverageLength() {
        StringOperation stringOperation = new StringOperation();
        int num = 2;
        int actual = stringOperation.getAverageLength("qwe", "abc") / num;
        int expected = 4;
        Assert.assertNotEquals(actual, expected);
    }

    @Test()
    public void testIsEqualsLength() {
        StringOperation stringOperation = new StringOperation();
        boolean actual = stringOperation.isEqualsLength("qwe", "abc");
        Assert.assertEquals(actual, true);
    }

    @Test()
    public void testIsIsFirstLonger() {
        StringOperation stringOperation = new StringOperation();
        boolean actual = stringOperation.isFirstLonger("qwe", "abc");
        Assert.assertEquals(actual, false);
    }
}