package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;

public class MainPage extends BaseForm {

    private By installSteamButtonXpath = By.xpath("//a[contains(@class, 'header_installsteam_btn_content')]");
    private By gamesDropdownXpath = By.xpath("//div[@id='genre_tab']");
    public Header header;

    public MainPage() {
        this.header = new Header();
    }

    //Header header = new Header();
    //MainPage mainPage = new MainPage(header);

    public void clickOnGameStyle(By element) {
        Button actionButton = new Button(Browser.getDriver().findElement((By) element));
        actionButton.click();
    }

    //public List<String> getGamesItems() {
    // Dropdown gamesDropdown = new Dropdown(Browser.getDriver().findElement(gamesDropdownXpath));
    // return gamesDropdown.getItems();
    //}

}
