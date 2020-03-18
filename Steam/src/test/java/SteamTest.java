import browsers.Browsers;
import browsers.DriverFactory;
import configuration.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pages.GameWithMaxDiscountPage;
import pages.GamesPage;
import pages.MainPage;
import pages.InstallToSteamPage;
import parser.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

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
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.getInstance("firefox");
        driver.manage().window().maximize();
        localization = System.getenv("localization");

        if (localization != null) {
            if (localization.equals("ru")) {
                logger.info("Localization is not ru");
                xmlPath = "testDataRu.xml";
            } else if (localization.equals("com")) {
                xmlPath = "testDataCom.xml";
                logger.info("Localization is not ru");
            } else {
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
    public void loginAndDownload() throws IOException, SAXException, ParserConfigurationException {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        InstallToSteamPage installToSteamPage = mainPage.clickInstallSteamButton();
        Assert.assertTrue(installToSteamPage.welcomeToSteamPageIsLoaded());
        logger.info("Install Steam page is opened");
        installToSteamPage.clickInstallSteamNowButton();
        Assert.assertTrue(installToSteamPage.setupFileIsDownloaded(xmlParser.getValueFromXML(xmlPath, "file")),
                "File didn't download");
        logger.info("The file has been downloaded");
    }

    @Test
    public void chooseGameWithDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        GamesPage gamesPage = mainPage.clickOnAction();
        Assert.assertTrue(gamesPage.actionPageIsLoaded(xmlParser.getValueFromXML(xmlPath, "action")),
                "Action games page didn't load");
        logger.info("Action games page is opened");
        gamesPage.clickOnTopSellers();
        List<WebElement> discountGames = gamesPage.getDiscountList();
        WebElement gameWithMaxDiscount = gamesPage.getMaxDiscountGame(discountGames);
        Double initialPrice = gamesPage.getGameInitialPrice(gameWithMaxDiscount);
        Double newPrice = gamesPage.getGameNewPrice(gameWithMaxDiscount);
        Double discountValue = gamesPage.getGameDiscountValue(gameWithMaxDiscount);
        GameWithMaxDiscountPage gameWithMaxDiscountPage = gamesPage.chooseGame(gameWithMaxDiscount);
        Assert.assertTrue(initialPrice == gameWithMaxDiscountPage.compareOldPrice(gameWithMaxDiscount),
                "Prices are not equals");
        Assert.assertTrue(newPrice == gameWithMaxDiscountPage.compareNewPrice(gameWithMaxDiscount),
                "Prices are not equals");
        Assert.assertTrue(discountValue == gameWithMaxDiscountPage.compareDiscount(gameWithMaxDiscount),
                "Discount values are not equals");
    }

    @Test
    public void chooseGameWithLowestDiscount() throws ParserConfigurationException, SAXException, IOException {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        logger.info("Main page loaded successfully");
        GamesPage gamesPage = mainPage.clickOnIndie();
        Assert.assertTrue(gamesPage.indiePageIsLoaded(xmlParser.getValueFromXML(xmlPath, "indie")),
                "Indie games page didn't load");
        logger.info("Indie games page is opened");
        gamesPage.clickOnTopSellers();
    }

    @AfterMethod
    public void driverQuit() {
        Browsers.quitDriver();
    }
}