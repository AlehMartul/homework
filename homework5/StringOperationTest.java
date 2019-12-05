import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class StringOperationTest {

    @DataProvider
    public Object [][] dataProviderGetStrings(){
        return new Object[][]{{"abd", 3}, {"", 0}, {"  hhh  ", 7}};
    }
    @DataProvider
    public Object [][] dataProviderGetSum(){
        return new Object[][]{{"abd", "wwe", "abdwwe"}, {"", "q", "q"}, {"te", "te", "tete"}};
    }

    @Test (dataProvider = "dataProviderGetSum")
    public void testGetSum(String str1, String str2, String expected) {
    StringOperation stringOperation = new StringOperation();
    String actual = stringOperation.getSum(str1, str2);
    Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetReverseSum() {
       StringOperation stringOperation = new StringOperation();
       String actual = stringOperation.getReverseSum("ble", "isi", "inv");
       String expected = "invisible";
       Assert.assertEquals(actual, expected);
    }

    @Test (dataProvider = "dataProviderGetStrings")
    public void testGetLength(String str, int expected) {
        StringOperation stringOperation = new StringOperation();
        int actual = stringOperation.getLength(str);
        Assert.assertEquals(actual, expected);
    }
}