package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamesPage extends BasePage {
    public GamesPage(WebDriver driver) {
        super(driver);
    }

    private By topSellers = By.xpath(".//*[contains(@id, 'tab_select_TopSellers')]");
    private By gamePageHeader = By.xpath(".//*[@class='pageheader']");
    private By topSellersActive = By.xpath(".//*[contains(@id, 'tab_select_TopSellers')]");
    private By discount = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']");
    private By discountOfGame = By.xpath(".//div[@class='discount_pct']");
    private By oldPrice = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_original_price']");
    private By newPrice = By.xpath("//*[@id='TopSellersRows']//*[@class='discount_final_price']");
    private By gamesWithDiscount = By.xpath("//*[@id='TopSellersRows']//div[@class='discount_original_price']" +
            "//ancestor::div[@class='discount_block tab_item_discount']");

    public boolean gamesPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(topSellers));
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
        for (WebElement game : games) {
            Double discountValueInPercentage = Double.parseDouble(game.findElement(discountOfGame).getText()
                    .replace("%", "")
                    .replace("-", ""));
            discountsOfGames.put(game, discountValueInPercentage);
        }
        Map.Entry<WebElement, Double> maxDiscountInGame = null;
        for (Map.Entry<WebElement, Double> entry : discountsOfGames.entrySet()) {
            if (maxDiscountInGame == null || entry.getValue().compareTo(maxDiscountInGame.getValue()) > 0) {
                maxDiscountInGame = entry;
            }
        }
        return maxDiscountInGame.getKey();
    }

   // public GameWithMaxDiscountPage chooseGame(WebElement game) {
   //     clickOnElement((By) game);
   //     return new GameWithMaxDiscountPage(getDriver());
   // }

    public GameWithMaxDiscountPage chooseGame(WebElement game) {
        game.click();
        return new GameWithMaxDiscountPage(getDriver());
    }

    public Double getGameInitialPrice(WebElement game) {
        return Double.parseDouble(game.findElement(oldPrice).getText().replace("$", ""));
    }

    public Double getGameDiscountValue(WebElement game) {
        return Double.parseDouble(game.findElement(discount).getText().replace("%", "").replace("-", ""));
    }
    public Double getGameNewPrice(WebElement game) {
        return Double.parseDouble(game.findElement(newPrice).getText().replace("$", ""));
    }

    public boolean actionPageIsLoaded(String toSearch) {
        chosenPageIsLoaded(gamePageHeader, toSearch);
        return true;
    }

    public boolean indiePageIsLoaded(String toSearch) {
        chosenPageIsLoaded(gamePageHeader, toSearch);
        return true;
    }
}
