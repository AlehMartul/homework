package pages;

import browsers.Browsers;
import configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class BasePage {
    private static final int TIMEOUT = Integer.parseInt(Configuration.getData("timeout"));
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String TEST_DATA = Configuration.getData("testData");

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Integer.parseInt(Configuration.getData("timeout")));
    }

    public boolean elementIsPresent(WebElement element) {
        return new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void clickOnElement(By element) {
        getDriver().findElement(element).click();
    }

    public void moveMouseToElement(By element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement((WebElement) element).build().perform();
    }

    public boolean chosenPageIsLoaded(By element, String name){
        String nameOfThePage = getDriver().findElement(element).getText();
        if (nameOfThePage.contains(name)){
            return true;
        }
        return false;
    }

    public String getValueFromXML() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(TEST_DATA);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        String tagValue = document.getElementsByTagName("actionRus").item(0).getTextContent();
        return tagValue;
    }

    public WebElement expectClickable(WebElement webElement) {
        return new WebDriverWait(Browsers.getDriver("chrome"), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void expectVisibility(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOf(webElement));
    }
}
