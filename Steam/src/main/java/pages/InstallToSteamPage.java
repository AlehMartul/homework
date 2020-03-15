package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InstallToSteamPage extends BasePage {
    public InstallToSteamPage(WebDriver driver) {
        super(driver);
    }

    private By installSteamNowButton = By.xpath("//*[contains(@class, 'about_install_steam_link')]");

    public boolean welcomeToSteamPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(installSteamNowButton));
    }

    public void clickInstallSteamNowButton(){
        clickOnElement(installSteamNowButton);
    }

    public void waitForDownload(){
        clickOnElement(installSteamNowButton);
//        wai for download to be complete
    }

}
