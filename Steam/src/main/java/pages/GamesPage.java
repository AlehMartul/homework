package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamesPage extends BasePage {
    public GamesPage(WebDriver driver) {
        super(driver);
    }

    private By topSellers = By.xpath(".//*[contains(@id, 'tab_select_TopSellers')]");
    private By gamePageHeader = By.xpath(".//*[@class='pageheader']");
    private By actionGames = By.xpath(".//*[contains(@href," +
            " 'https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/?snr=1_241_4_action_12')]");
    private By indieGames = By.xpath("");
    private By topSellersActive = By.xpath(".//*[contains(@id, 'tab_select_TopSellers')]");
    private By discount = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']");
    private By oldPrice = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_original_price']");
    private By newPrice = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_final_price']");
    private By gamesWithDiscount = By.xpath("//*[@id='TopSellersRows']//div[@class='discount_original_price']" +
            "//ancestor::div[@class='discount_block tab_item_discount']");
    private By discountValue = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']");

    public boolean gamesPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(topSellers));
    }

    public GamesPage clickOnAction() {
        clickOnElement(new MainPage(getDriver()).getActions());
        return new GamesPage(getDriver());
    }

    public GamesPage clickOnIndie() {
        clickOnElement(new MainPage(getDriver()).getIndie());
        return new GamesPage(getDriver());
    }

    public GamesPage clickOnTopSellers() {
        clickOnElement(topSellers);
        return this;
    }

    public List<WebElement> getDiscountList() {
        return getDriver().findElements(gamesWithDiscount);
    }

    public WebElement getMaxDiscountGame(List<WebElement> games) {
        Map<WebElement, Double> discountsOfGames = new HashMap<>();
        for(WebElement game : games) {
            Double discountValueInPercentage = Double.parseDouble(game.findElement(discountValue).getText()
                    .replace("%", "")
                    .replace("-", ""));
            discountsOfGames.put(game, discountValueInPercentage);
        }

        Map.Entry<WebElement, Double> maxDiscountInGame = null;
        for (Map.Entry<WebElement, Double> entry : discountsOfGames.entrySet())
        {
            if (maxDiscountInGame == null || entry.getValue().compareTo(maxDiscountInGame.getValue()) > 0)
            {
                maxDiscountInGame = entry;
            }
        }
        return maxDiscountInGame.getKey();
    }

    public GameWithMaxDiscount chooseGame(WebElement game) {
        game.click();
        return new GameWithMaxDiscount(getDriver());
    }

    public Double getGameInitialPrice(WebElement game) {
        return Double.parseDouble(game.findElement(oldPrice).getText().replace("$", ""));
    }

    public Double getGameNewPrice(WebElement game) {
        return Double.parseDouble(game.findElement(newPrice).getText().replace("$", ""));
    }

    public List<WebElement> getDiscountListWebEl() {
        List<WebElement> discountListWebEl = getDriver().findElements(gamesWithDiscount);
        return discountListWebEl;
    }

    public void print() {
        System.out.println(getDiscountList());
    }

    public boolean actionPageIsLoaded(String toSearch) throws IOException, SAXException, ParserConfigurationException {
        chosenPageIsLoaded(gamePageHeader, toSearch);
        return true;
    }

    public boolean indiePageIsLoaded(String toSearch) throws IOException, SAXException, ParserConfigurationException {
        chosenPageIsLoaded(gamePageHeader, toSearch);
        return true;
    }
}
