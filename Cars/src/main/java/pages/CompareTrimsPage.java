package pages;

import org.openqa.selenium.By;

public class CompareTrimsPage extends BaseForm{

    private By title = By.xpath("//h1[@class='cui-heading-1']");
    private By engine = By.xpath("class='cell cell-bg grow-2'");
    private By transmission = By.xpath("//div[@class='cell grow-2']");

}
