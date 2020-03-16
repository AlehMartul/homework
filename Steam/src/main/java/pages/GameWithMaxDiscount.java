package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GameWithMaxDiscount extends BasePage{

    public GameWithMaxDiscount(WebDriver driver) {
        super(driver);
    }

    public GameWithMaxDiscount clickOnGameWithMaxDiscountRate() {
        clickOnElement((By) new GamesPage(getDriver()).getDiscountListWebEl().get(0));
        return new GameWithMaxDiscount(getDriver());
    }
}
