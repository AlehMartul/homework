import browsers.Browser;
import configuration.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pages.GameWithDiscountPage;
import pages.GamesPage;
import pages.MainPage;
import pages.InstallToSteamPage;
import parser.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SteamTest {
    private Browser browser = new Browser();
    private By actionGames = By.xpath
            (".//*[@href='https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/?snr=1_4_4__125']");
    private By indieGames = By.xpath
            (".//*[@href='https://store.steampowered.com/tags/ru/%D0%98%D0%BD%D0%B4%D0%B8/?snr=1_4_4__125']");
    private String localization;
    private String xmlPath;
    private XMLParser xmlParser;
    private MainPage mainPage;
    private int forMaxDiscount = 0;
    private int forMinDiscount = 100;
    private static final Logger logger = LogManager.getLogger(SteamTest.class);

    public SteamTest() {
    }

    @BeforeMethod
    public void createDriver() {
        Browser.getDriver();
        browser.fullScreenMode();
        new Configuration().setLocalization();
        xmlParser = new XMLParser();
        browser.getMainUrl(Configuration.getData("mainUrl"));
    }

    @Test
    public void loginAndDownload() throws IOException, SAXException, ParserConfigurationException {
        MainPage mainPage = new MainPage();
        //List<String> gamesItems = mainPage.getGamesItems();
        //Assert.assertTrue(gamesItems.contains("Free to Play"));
        Assert.assertTrue(mainPage.header.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        InstallToSteamPage installToSteamPage = new InstallToSteamPage();
        mainPage.header.clickInstallSteamButton();
        Assert.assertTrue(installToSteamPage.welcomeToSteamPageIsLoaded());
        logger.info("Install Steam page is opened");
        installToSteamPage.clickInstallSteamNowButton();
        Assert.assertTrue(installToSteamPage.setupFileIsDownloaded(xmlParser.getValueFromXML(xmlPath, "file")),
                "File didn't download");
        logger.info("The file has been downloaded");
    }

    @Test
    public void chooseGameWithDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.header.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        GamesPage gamesPage = new GamesPage();
        //1st parameter
        mainPage.clickOnGameStyle(actionGames);
        //2nd parameter
        Assert.assertTrue(gamesPage.actionPageIsLoaded(xmlParser.getValueFromXML(xmlPath, "action")),
                "Games page didn't load");
        logger.info("Games page is opened");
        gamesPage.clickOnTopSellers();
        List<WebElement> discountGames = gamesPage.getDiscountList();
        //3rd parameter
        WebElement gameWithDesiredDiscount = gamesPage.getDiscountGame(discountGames, forMaxDiscount);
        Double initialPrice = gamesPage.getGameInitialPrice(gameWithDesiredDiscount);
        Double newPrice = gamesPage.getGameNewPrice(gameWithDesiredDiscount);
        Double discountValue = gamesPage.getGameDiscountValue(gameWithDesiredDiscount);
        GameWithDiscountPage gameWithMaxDiscountPage = new GameWithDiscountPage();
        gamesPage.chooseGame(gameWithDesiredDiscount);
        Assert.assertTrue(gameWithMaxDiscountPage.gamePageIsLoaded(), "Game page with max discount didn't load");
        logger.info("Game page with max discount is opened");
        Assert.assertTrue(initialPrice.equals(gameWithMaxDiscountPage.compareOldPrice(gameWithDesiredDiscount)),
                "Prices are not equals");
        Assert.assertTrue(gameWithMaxDiscountPage.compareNewPrice(gameWithDesiredDiscount).equals(newPrice),
                "Prices are not equals");
        Assert.assertTrue(gameWithMaxDiscountPage.compareDiscount(gameWithDesiredDiscount).equals(discountValue),
                "Discount values are not equals");
    }

    @Test
    public void chooseGameWithLowestDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.header.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        GamesPage gamesPage = new GamesPage();
        mainPage.clickOnGameStyle(indieGames);
        Assert.assertTrue(gamesPage.indiePageIsLoaded(xmlParser.getValueFromXML(xmlPath, "indie")),
                "Indie games page didn't load");
        logger.info("Indie games page is opened");
        gamesPage.clickOnTopSellers();
        List<WebElement> discountGames = gamesPage.getDiscountList();
        WebElement gameWithDesiredDiscount = gamesPage.getDiscountGame(discountGames, forMinDiscount);
        Double initialPrice = gamesPage.getGameInitialPrice(gameWithDesiredDiscount);
        Double newPrice = gamesPage.getGameNewPrice(gameWithDesiredDiscount);
        Double discountValue = gamesPage.getGameDiscountValue(gameWithDesiredDiscount);
        GameWithDiscountPage gameWithMinDiscountPage = new GameWithDiscountPage();
        gamesPage.chooseGame(gameWithDesiredDiscount);
        Assert.assertTrue(gameWithMinDiscountPage.gamePageIsLoaded(), "Game page with min discount didn't load");
        logger.info("Game page with min discount is opened");
        Assert.assertTrue(gameWithMinDiscountPage.compareOldPrice(gameWithDesiredDiscount).equals(initialPrice),
                "Prices are not equals");
        Assert.assertTrue(gameWithMinDiscountPage.compareNewPrice(gameWithDesiredDiscount).equals(newPrice),
                "Prices are not equals");
        Assert.assertTrue(gameWithMinDiscountPage.compareDiscount(gameWithDesiredDiscount).equals(discountValue),
                "Discount values are not equals");
    }

    @AfterMethod
    public void driverQuit() {
        Browser.quitDriver();
    }
}