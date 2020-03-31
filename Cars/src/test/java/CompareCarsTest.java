import browsers.Browser;
import configuration.Configuration;
import models.Car;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import parser.XMLParser;

import java.util.List;

import static org.apache.log4j.LogManager.getLogger;

public class CompareCarsTest {

    private static final Logger logger = getLogger(CompareCarsTest.class);

    private Browser browser = new Browser();
    private String xmlPath;
    private String transmission = "Transmission";
    private String engine = "Engine";
    private XMLParser xmlParser;
    private int yearTitle = 0;
    private int madeTitle = 1;
    private int modelTitle = 2;


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
        Assert.assertTrue(mainPage.mainPageIsLoaded(), "Main page didn't load");
        mainPage.clickOnResearchButton();
        ResearchPage researchPage = new ResearchPage();
        Assert.assertTrue(researchPage.researchPageIsOpened(), "Research page didn't load");
        researchPage.chooseRandomCar();
        CarPage carPage = new CarPage();
        Car car1 = new Car();
        car1.setYear(carPage.getInfoAboutCar(yearTitle));
        car1.setMade(carPage.getInfoAboutCar(madeTitle));
        car1.setModel(carPage.getInfoAboutCar(modelTitle));
        carPage.clickOnResearchButton();
        Assert.assertTrue(researchPage.researchPageIsOpened(), "Research page didn't load");
        researchPage.chooseRandomCar();
        Car car2 = new Car();
        car2.setYear(carPage.getInfoAboutCar(yearTitle));
        car2.setMade(carPage.getInfoAboutCar(madeTitle));
        car2.setModel(carPage.getInfoAboutCar(modelTitle));
        carPage.clickOnResearchButton();
        researchPage.clickSearchSideBySideButton();
        SideBySidePage sideBySidePage = new SideBySidePage();
        Assert.assertTrue(sideBySidePage.sideBySideComparePageIsOpened(), "Side by side compare page didn't load");

        CarPageSideBySide carPageSideBySide  = new CarPageSideBySide();
        car1.setEngine(carPageSideBySide.getGearValue(engine));
        car1.setTransmission(carPageSideBySide.getGearValue(transmission));


    }

    @AfterMethod
    public void driverQuit() {
        Browser.quitDriver();
    }
}