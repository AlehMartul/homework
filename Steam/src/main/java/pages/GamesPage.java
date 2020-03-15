package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean gamesPageIsLoaded() {
        return new BasePage(getDriver()).elementIsPresent(getDriver().findElement(topSellers));
    }

    public void clickOnAction() {

    }

    public void clickOnTopSellers() {
        clickOnElement(topSellers);
    }

    public List<String> getDiscountList() {
        List<String> discountList = new ArrayList<>();
        List<WebElement> discountListWebEl = getDriver().findElements(gamesWithDiscount);
        for (WebElement element : discountListWebEl) {
            discountList.add(element.getText());
        }
        return discountList;
    }

    public List<WebElement> getDiscountListWebEl() {
        List<WebElement> discountListWebEl = getDriver().findElements(gamesWithDiscount);
        return discountListWebEl;
    }

    public void clickOnGameWithMaxDiscountRate() {
        clickOnElement((By) getDiscountListWebEl().get(0));
    }

    public void print() {
        System.out.println(getDiscountList());
    }

    public boolean actionPageIsLoaded() throws IOException, SAXException, ParserConfigurationException {
        chosenPageIsLoaded(gamePageHeader, getValueFromXML());
        return true;
    }

    public boolean indiePageIsLoaded(){
        chosenPageIsLoaded(gamePageHeader, "Инди");
        return true;
    }

}
