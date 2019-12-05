import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringOperation5Test {

    @DataProvider
    public Object[][] dataProviderGetTotalLength() {
        return new Object[][]{{"abd", "cda", 6}, {"", "a", 1}, {"  ", " c ", 5}};
    }
    @DataProvider
    public Object[][] dataProviderGetAverageLength() {
        return new Object[][]{{"abd", "cda", 2}, {"", "ac", 2}, {" 1 ", " ", 3}};
    }

    @Test(dataProvider = "dataProviderGetTotalLength")
    public void testGetTotalLength(String str, String str2, int expected) {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getLength(str) + stringOperation.getLength(str2);
        Assert.assertEquals(actual, expected);
    }
    @Test(dataProvider = "dataProviderGetAverageLength", enabled = false)
    public void testGetAverageLength(String str, String str2, int expected) {
        StringOperation stringOperation = new StringOperation();
        int num = 2;
        int actual = (stringOperation.getLength(str) + stringOperation.getLength(str2))/num;
        Assert.assertNotEquals(actual, expected);
    }
}