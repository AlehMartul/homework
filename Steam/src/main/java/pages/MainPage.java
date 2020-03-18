package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webelements.Button;
import webelements.Dropdown;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage open(String url) {
        getDriver().get(url);
        return new MainPage(getDriver());
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By header = By.id("global_action_menu");
    private By installSteamButtonXpath = By.xpath("//*[contains(@class, 'header_installsteam_btn_content')]");
    private By gamesButtonXpath = By.xpath(".//*[@id='genre_tab']");
    private By actionGames = By.xpath
            (".//*[@href='https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/?snr=1_4_4__125']");
    private By indieGames = By.xpath
            (".//*[@href='https://store.steampowered.com/tags/ru/%D0%98%D0%BD%D0%B4%D0%B8/?snr=1_4_4__125']");
    private By gamesDropdownXpath = By.xpath("//*[@id='genre_tab']");

    public boolean mainPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(header));
    }

    public InstallToSteamPage clickInstallSteamButton() {
        Button steamButton = new Button(getDriver().findElement(installSteamButtonXpath));
        steamButton.click();
        return new InstallToSteamPage(getDriver());
    }

    public GamesPage clickOnAction() {
        clickOnElement(actionGames);
        return new GamesPage(getDriver());
    }

    public GamesPage clickOnIndie() {
        clickOnElement(indieGames);
        return new GamesPage(getDriver());
    }

    public List<String> getGamesItems(){
        Dropdown gamesDropdown = new Dropdown(getDriver().findElement(gamesDropdownXpath));
        return gamesDropdown.getItems();
    }
}
