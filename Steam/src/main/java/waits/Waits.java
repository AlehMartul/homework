package waits;

import browsers.Browsers;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    private static final int TIMEOUT = Integer.parseInt(Configuration.getData("timeout"));
    private WebDriver driver = Browsers.getDriver("chrome");

    public WebElement expectClickable(WebElement webElement) {
        return new WebDriverWait(Browsers.getDriver("chrome"), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void expectVisibility(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOf(webElement));
    }
}
