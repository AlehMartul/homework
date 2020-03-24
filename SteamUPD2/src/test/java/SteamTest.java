import browsers.Browser;
import configuration.Configuration;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GameWithDiscountPage;
import pages.GamesPage;
import pages.MainPage;
import pages.InstallToSteamPage;
import parser.XMLParser;

import java.util.List;

public class SteamTest {
    private Browser browser = new Browser();
    private String xmlPath;
    private XMLParser xmlParser;

    public SteamTest() {
    }

    @DataProvider(name = "Provider")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]{{"action", true}, {"indie", false}};
    }

    @BeforeMethod
    public void createDriver() {
        Browser.getDriver();
        browser.fullScreenMode();
        xmlParser = new XMLParser();
        browser.getMainUrl(Configuration.getData("mainUrl"));
    }

    @Test
    public void loginAndDownload() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.header.mainPageIsLoaded(), "Main page didn't load");
        InstallToSteamPage installToSteamPage = new InstallToSteamPage();
        mainPage.header.clickInstallSteamButton();
        Assert.assertTrue(installToSteamPage.welcomeToSteamPageIsLoaded());
        installToSteamPage.clickInstallSteamNowButton();
        xmlPath = new Configuration().getLocalization();
        Assert.assertTrue(installToSteamPage.setupFileIsDownloaded(xmlParser.getValueFromXML(xmlPath, "file")),
                "File didn't download");
    }

    @Test(dataProvider = "Provider")
    public void chooseGameWithDiscount(String actionOrIndie, boolean maxOrMinDiscount) {
        MainPage mainPage = new MainPage();
        xmlPath = new Configuration().getLocalization();
        Assert.assertTrue(mainPage.header.mainPageIsLoaded(), "Main page didn't load");
        GamesPage gamesPage = new GamesPage();
        String getGenreFromXML = xmlParser.getValueFromXML(xmlPath, actionOrIndie);
        mainPage.menuGoTo(getGenreFromXML);
        Assert.assertTrue(gamesPage.genrePageIsLoaded(getGenreFromXML),
                "Games page didn't load");
        gamesPage.clickOnTopSellers();
        List<WebElement> discountGames = gamesPage.getDiscountList();

        WebElement gameWithDesiredDiscount;
        if (maxOrMinDiscount == true) {
            gameWithDesiredDiscount = gamesPage.getMaxDiscountGame(discountGames);
        } else {
            gameWithDesiredDiscount = gamesPage.getMinDiscountGame(discountGames);
        }
        Double initialPriceFromList = gamesPage.getGameInitialPrice(gameWithDesiredDiscount);
        Double newPriceFromList = gamesPage.getGameNewPrice(gameWithDesiredDiscount);
        Double discountValueFromList = gamesPage.getGameDiscountValue(gameWithDesiredDiscount);
        GameWithDiscountPage gameWithDiscountPage = new GameWithDiscountPage();
        gamesPage.chooseGame(gameWithDesiredDiscount);
        Assert.assertTrue(gameWithDiscountPage.gamePageIsLoaded(), "Game page with max discount didn't load");
        Assert.assertTrue(initialPriceFromList.equals(gameWithDiscountPage.getOldPrice()),
                "Prices are not equals");
        Assert.assertTrue(newPriceFromList.equals(gameWithDiscountPage.getNewPrice()),
                "Prices are not equals");
        Assert.assertTrue(discountValueFromList.equals(gameWithDiscountPage.getDiscountValue()),
                "Discount values are not equals");
    }

    @AfterMethod
    public void driverQuit() {
        Browser.quitDriver();
    }
}