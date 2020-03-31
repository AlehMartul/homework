package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;
import webelements.Label;

public class MainPage extends BaseForm {

    private By loginButton = By.xpath(".//a[@title = 'login or signup']");
    private By researchButtonXPath = By.xpath(".//ul[contains(@class, 'U4')]//a[@href='/research/']");
    private By logo = By.xpath("//img[@id='cars-com-logo']");

    public boolean mainPageIsLoaded() {
        return isDisplayed(Browser.getDriver().findElement(logo));
    }

    public void clickOnResearchButton() {
        Button researchButton = new Button(Browser.getDriver().findElement(researchButtonXPath));
        researchButton.click();
    }

    public void returnOnMainPage() {
        Label mainLogo = new Label(Browser.getDriver().findElement(logo));
        mainLogo.click();
    }

}
