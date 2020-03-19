package waits;

import browsers.Browser;
import configuration.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Waits {

    private static final int TIMEOUT = Integer.parseInt(Configuration.getData("timeout"));
    private static final Logger logger = LogManager.getLogger(Waits.class);

    public Waits() {
    }

    public WebElement expectClickable(WebElement webElement) {
        logger.info("Waiting for clickable of element");
        return new WebDriverWait(Browser.getDriver(), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void expectVisibility(WebElement webElement) {
        logger.info("Waiting for visibility of element");
        new WebDriverWait(Browser.getDriver(), TIMEOUT).
                until(ExpectedConditions.visibilityOf(webElement));
    }

    public boolean fileIsDownloaded(String name) {
        logger.info("Waiting for downloading of element");
        new WebDriverWait(Browser.getDriver(), TIMEOUT);
        return new File(name).exists();
    }

    public boolean elementIsPresent(WebElement element) {
        return new WebDriverWait(Browser.getDriver(), TIMEOUT).
                until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

}
