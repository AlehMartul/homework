package pages;

import browsers.Browser;
import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import webelements.Button;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Random;

public class ResearchPage extends BaseForm {

    private By searchButtonXPath = By.xpath("//input[@value='Search']");
    private By searchSideBySide = By.xpath(".//a[@data-linkname='compare-cars']");
    private By selectMade = By.xpath("//select[@name='makeId']");
    private By selectModel = By.xpath("//select[@name='modelId']");
    private By selectYear = By.xpath("//select[@name='year']");
    private List<WebElement> options = null;

    public boolean researchPageIsOpened() {
        return isDisplayed(Browser.getDriver().findElement(searchButtonXPath));
    }

    public void clickSearchButton() {
        Button searchButton = new Button(Browser.getDriver().findElement(searchButtonXPath));
        searchButton.click();
    }

    public void chooseRandomFromDropdown(By dropdown) {
        WebElement drpDwnList = Browser.getDriver().findElement(dropdown);
        Select objSel = new Select(drpDwnList);
        options = objSel.getOptions();
        int iCnt = options.size();
        Random num = new Random();
        int iSelect = num.nextInt(iCnt);
        objSel.selectByIndex(iSelect);
    }

    public void selectMadeFromDropdown() {
        chooseRandomFromDropdown(selectMade);
    }

    public void selectModelFromDropdown() {
        chooseRandomFromDropdown(selectModel);
    }

    public void selectYearFromDropdown() {
        chooseRandomFromDropdown(selectYear);
    }

    public void chooseRandomCar(){
        selectMadeFromDropdown();
        selectModelFromDropdown();
        selectYearFromDropdown();
        clickSearchButton();
    }

    public void clickSearchSideBySideButton() {
        Button searchSideBySideButton = new Button(Browser.getDriver().findElement(searchSideBySide));
        searchSideBySideButton.click();
    }


}
