package webelements;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Label extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Label.class);

    public Label(WebElement webElement) {
        super(webElement);
    }

}