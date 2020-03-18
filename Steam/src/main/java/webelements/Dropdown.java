package webelements;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;

public class Dropdown extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Dropdown.class);

    private By listXpath = By.xpath("//*[@class='popup_menu_item']");

    public Dropdown(WebElement webElement) {
        super(webElement);
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        assertNotNull(webElement);
        for(WebElement item : webElement.findElements(listXpath)) {
            items.add(item.getText());
        }
        return items;
    }
}
