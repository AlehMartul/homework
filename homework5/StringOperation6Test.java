import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class StringOperation6Test {
    @DataProvider
    public Object[][] dataProviderIsFirstLonger() {
        return new Object[][]{{"abd", "cda", false}, {"", "a", false}, {"  ", " c", true}};
    }

    @Test(dataProvider = "dataProviderIsFirstLonger")
    public void isFirstLonger(String line1, String line2, boolean expected) {
        StringOperation stringOperation = new StringOperation();

        boolean actual = (stringOperation.getLength(line1) > stringOperation.getLength(line2));

        Assert.assertFalse(actual);
    }

}