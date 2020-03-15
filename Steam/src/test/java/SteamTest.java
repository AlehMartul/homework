import browsers.Browsers;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pages.GamesPage;
import pages.MainPage;
import pages.WelcomeToSteamPage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SteamTest {
    private WebDriver driver;
    private String browser = Configuration.getData("browser");

    @BeforeMethod
    public void createDriver() {
        driver = Browsers.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(Configuration.getData("mainUrl"));
    }

    @Test
    public void loginAndDownload() {
        WelcomeToSteamPage steamPage = new WelcomeToSteamPage(driver);
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        mainPage.clickInstallSteamButton();
        Assert.assertTrue(steamPage.welcomeToSteamPageIsLoaded());
        steamPage.clickInstallSteamNowButton();
    }

    @Test
    public void chooseGameWithDiscount() throws ParserConfigurationException, SAXException, IOException {
        //MainPage mainPage = new MainPage(driver);
        GamesPage gamesPage = new GamesPage(driver);
        //Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        //mainPage.getGameMenu();
        //mainPage.chooseActions();
        driver.get("https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/");
        Assert.assertTrue(gamesPage.actionPageIsLoaded(), "Action games page didn't load");
        gamesPage.clickOnTopSellers();
        //gamesPage.clickOnGameWithMaxDiscountRate();
        gamesPage.getDiscountList();
        gamesPage.print();
    }

    @Test
    public void chooseGameWithLowestDiscount() {
        MainPage mainPage = new MainPage(driver);
        GamesPage gamesPage = new GamesPage(driver);
        //Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        //mainPage.getGameMenu();
        //mainPage.chooseIndie();
        driver.get("https://store.steampowered.com/tags/ru/%D0%98%D0%BD%D0%B4%D0%B8/");
        Assert.assertTrue(gamesPage.indiePageIsLoaded(), "Indie games page didn't load");
        gamesPage.clickOnTopSellers();
        gamesPage.getDiscountList();
        gamesPage.print();
    }


    @AfterMethod
    public void driverQuit() {
        Browsers.quitDriver();
    }

}