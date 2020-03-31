package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;

public class CarPageSideBySide extends BaseForm {

    private By title = By.xpath(".//h2[@class='compare-subhead-h2']");
    private By addAnotherCar = By.xpath(".//div[@class='add-car-icon']");
    private By engineXpath =
            By.xpath(".//cars-compare-compare-info[@header='Engine']//p[contains(@ng-repeat, 'listItem in value')]");
    private By transXpath = By.xpath
            (".//cars-compare-compare-info[@header='Transmission']//p[contains(@ng-repeat, 'listItem in value')]");
    private String firstHalfOfTheXpath = ".//cars-compare-compare-info[@header='";
    private String secondHalfOfTheXpath = "']//p[contains(@ng-repeat, 'listItem in value')]";


    public String getGearValue(String gear) {
        String engOrTrans = Browser.getDriver().findElement
                (By.xpath(String.format(firstHalfOfTheXpath + gear + secondHalfOfTheXpath))).getText();
        return engOrTrans;
    }

    public String getEngineValue() {
        String engine = Browser.getDriver().findElement(engineXpath).getText();
        return engine;
    }

    public String getTransmissionValue() {
        String transmission = Browser.getDriver().findElement(transXpath).getText();
        return transmission;
    }

    public void clickOnAddAnotherCarButton() {
        Button addAnotherCarButton = new Button(Browser.getDriver().findElement(addAnotherCar));
        addAnotherCarButton.click();
    }

}