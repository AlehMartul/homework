package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }
        private By header = By.id("global_action_menu");
        private By installSteamButton = By.xpath("//*[contains(@class, 'header_installsteam_btn_content')]");
        private By gamesButton = By.xpath(".//*[@id='genre_tab']");
        private By actionGames = By.xpath(".//*[contains(@href," +
            " 'https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/?snr=1_241_4_action_12')]");
        private By indieGames = By.xpath(".//*[@href=" +
                "'https://store.steampowered.com/tags/ru/%D0%98%D0%BD%D0%B4%D0%B8/?snr=1_241_4_action_12']");

    public boolean mainPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(header));
    }

    public void clickInstallSteamButton(){
        clickOnElement(installSteamButton);
    }

    public void getGameMenu(){
        moveMouseToElement(gamesButton);
    }

    public void chooseActions(){
        clickOnElement(actionGames);
    }

    public void chooseIndie(){
        clickOnElement(indieGames);
    }

    }
