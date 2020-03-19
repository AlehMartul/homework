package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GameWithDiscountPage extends BaseForm {
    public GameWithDiscountPage() {
    }

    private By nameOfAGame = By.xpath("//div[@class='apphub_AppName']");
    private By discount = By.xpath("//div[@class='game_area_purchase_game_wrapper']//div[@class='discount_pct']");
    private By oldPrice = By.xpath
            ("//div[@class='game_area_purchase_game_wrapper']//div[@class='discount_original_price']");
    private By newPrice = By.xpath
            ("//div[@class='game_area_purchase_game_wrapper']//div[@class='discount_final_price']");

    public boolean gamePageIsLoaded() {
        return new BaseForm().isDisplayed(Browser.getDriver().findElement(nameOfAGame));
    }

    public Double compareOldPrice(WebElement game) {
        return Double.parseDouble(game.findElement(oldPrice).getText().replace("$", ""));
    }

    public Double compareNewPrice(WebElement game) {
        return Double.parseDouble(game.findElement(newPrice).getText().replace("$", ""));
    }

    public Double compareDiscount(WebElement game) {
        return Double.parseDouble(game.findElement(discount).getText().replace("%", "").replace("-", ""));
    }
}
