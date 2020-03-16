package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GameWithMaxDiscountPage extends BasePage {
    public GameWithMaxDiscountPage(WebDriver driver) {
        super(driver);
    }

    private By discount = By.xpath("//*[@data-price-final='643']//*[@class='discount_pct']");
    private By oldPrice = By.xpath("//*[@data-price-final='643']//*[@class='discount_original_price']");
    private By newPrice = By.xpath("//*[@data-price-final='643']//*[@class='discount_final_price']");

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
