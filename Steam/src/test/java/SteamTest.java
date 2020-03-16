import browsers.Browsers;
import configuration.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pages.GameWithMaxDiscount;
import pages.GamesPage;
import pages.MainPage;
import pages.InstallToSteamPage;
import parser.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class SteamTest {
    private WebDriver driver;
    private String browser = Configuration.getData("browser");
    private String localization;
    private String xmlPath;
    private XMLParser xmlParser;
    private MainPage mainPage;
    private static final Logger logger = LogManager.getLogger(SteamTest.class);

    @BeforeMethod
    public void createDriver() {
        driver = Browsers.getDriver(browser);
        driver.manage().window().maximize();
        localization = System.getenv("localization");

        if (localization != null) {
            if (localization.equals("ru")) {
                logger.info("Localization is not ru");
                xmlPath = "testDataRu.xml";
            } else if (localization.equals("com")) {
                xmlPath = "testDataCom.xml";
                logger.info("Localization is not ru");
            }
            else {
                logger.info("Localization is not allowed: " + localization);
            }
        } else {
            System.out.println("Localization is not specified");
            logger.info("Localization is not specified");
        }

        xmlParser = new XMLParser();
        mainPage = new MainPage(driver);
        mainPage = mainPage.open(Configuration.getData("mainUrl"));
    }

    @Test
    public void loginAndDownload() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        InstallToSteamPage installToSteamPage = mainPage.clickInstallSteamButton();
        Assert.assertTrue(installToSteamPage.welcomeToSteamPageIsLoaded());
        installToSteamPage.clickInstallSteamNowButton();
        installToSteamPage.waitForDownload();
    }

    @Test
    public void chooseGameWithDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage(driver);
        GamesPage gamesPage = new GamesPage(driver);
        GameWithMaxDiscount gameWithMaxDiscount =  new GameWithMaxDiscount(driver);
        //Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        //mainPage.getGameMenu();
        //gamesPage.clickOnAction();
        driver.get("https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/");
        Assert.assertTrue(gamesPage.actionPageIsLoaded(xmlParser.getValueFromXML(xmlPath, "action")),
                "Action games page didn't load");
        gamesPage.clickOnTopSellers();
        gameWithMaxDiscount.clickOnGameWithMaxDiscountRate();
        //gamesPage.getDiscountList();
        //gamesPage.print();
    }

    @Test
    public void chooseGameWithLowestDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage(driver);
        GamesPage gamesPage = new GamesPage(driver);
        //Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        //mainPage.getGameMenu();
        //mainPage.chooseIndie();
        driver.get("https://store.steampowered.com/tags/ru/%D0%98%D0%BD%D0%B4%D0%B8/");
        Assert.assertTrue(gamesPage.indiePageIsLoaded(xmlParser.getValueFromXML(xmlPath, "indie")),
                "Indie games page didn't load");
        gamesPage.clickOnTopSellers();
        gamesPage.getDiscountList();
        gamesPage.print();
    }

    @AfterMethod
    public void driverQuit() {
        Browsers.quitDriver();
    }
}