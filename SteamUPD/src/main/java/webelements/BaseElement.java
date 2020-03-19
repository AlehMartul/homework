package webelements;

import browsers.Browser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertNotNull;

public class BaseElement {
    protected WebElement webElement;
    private static final Logger logger = LogManager.getLogger(BaseElement.class);

    public BaseElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        assertNotNull(webElement);
        logger.info("Clicking on button with text '" + getText() + "'");
        webElement.click();
    }

    public void findElement() {
        assertNotNull(webElement);
        logger.info("Looking for element with text '" + getText() + "'");
        Browser.getDriver().findElement((By) webElement);
    }

    private String getText() {
        return webElement.getText();
    }
}