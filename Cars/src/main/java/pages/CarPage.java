package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;

public class CarPage extends BaseForm {

    private By title = By.xpath("//h1[@class='cui-page-section__title']");
    private By compareLinkXpath = By.xpath("//a[@data-linkname='compare-jump']");
    private By compareTrimsXpath = By.xpath("//a[@data-linkname='trim-compare']");
    private By researchButton = By.xpath(".//ul[@class='global-nav__parent']//a[@href='/research/']");

    public void clickOnCompareTrimsLink (){
        Button compareTrimsLink = new Button(Browser.getDriver().findElement(compareTrimsXpath));
    }

    public void clickOnResearchButton (){
        Button research = new Button(Browser.getDriver().findElement(researchButton));
        research.click();
    }

    public String getInfoAboutCar (int i) {
        String titleString = Browser.getDriver().findElement(title).getText();
        String[] words = titleString.split(" ");
        return words [i];
    }

}

