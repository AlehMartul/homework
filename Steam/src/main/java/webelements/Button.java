package webelements;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertNotNull;

public class Button extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Button.class);

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        assertNotNull(webElement);
        logger.info("Clicking on button with text '" + getText() + "'");
        webElement.click();
    }

    private String getText() {
        return webElement.getText();
    }
}
