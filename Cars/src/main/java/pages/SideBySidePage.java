package pages;

import browsers.Browser;
import org.openqa.selenium.By;

public class SideBySidePage extends BaseForm{

    private By startComparingXPath = By.xpath(".//button[@data-linkname='Compare-Now']");
    private By selectMake = By.xpath(".//select[@id='make-dropdown']");
    private By selectModel = By.xpath(".//select[@id='model-dropdown']");
    private By selectYear = By.xpath(".//select[@id='year-dropdown']");

    public boolean sideBySideComparePageIsOpened (){
        return isDisplayed(Browser.getDriver().findElement(startComparingXPath));
    }
}
