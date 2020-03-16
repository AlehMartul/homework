package pages;

import configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class BasePage {
    private static final int TIMEOUT = Integer.parseInt(Configuration.getData("timeout"));
    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
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

    public boolean chosenPageIsLoaded(By element, String name) {
        String nameOfThePage = getDriver().findElement(element).getText();
        if (nameOfThePage.contains(name)) {
            return true;
        }
        return false;
    }

    public boolean fileIsDownloaded(String name) {
        new WebDriverWait(driver, TIMEOUT);
        if (new File(name).exists()) {
            return true;
        } else {
            return false;
        }
    }
}