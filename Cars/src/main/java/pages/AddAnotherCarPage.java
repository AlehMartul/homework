package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;

public class AddAnotherCarPage extends BaseForm {

    private By selectMake = By.xpath(".//select[@id='make-dropdown']");
    private By selectModel = By.xpath(".//select[@id='model-dropdown']");
    private By selectYear = By.xpath(".//select[@id='year-dropdown']");
    private By buttonDone = By.xpath(".//button[@class='modal-button']");

    public void clickOnDoneButton() {
        Button doneButton = new Button(Browser.getDriver().findElement(buttonDone));
    }

}
