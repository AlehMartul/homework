import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Booking3 {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://www.booking.com/");
        WebElement search = chromeDriver.findElement(By.xpath(".//input[@name='ss']"));
        search.clear();
        search.sendKeys("Москва");
        WebElement dateComponent = chromeDriver.findElement(By.xpath("//*[contains(@class,'xp__date-time')][1]"));
        dateComponent.click();
        WebElement december28 = chromeDriver.findElement(By.xpath("//*[@class='bui-calendar__dates']//td[@data-date='2019-12-28']"));
        december28.click();
        WebElement december30 = chromeDriver.findElement(By.xpath("//*[@class='bui-calendar__dates']//td[@data-date='2019-12-30']"));
        december30.click();
        Thread.sleep(1000);
        search.submit();
        int quantity = chromeDriver.findElements(By.xpath("//*[@id='search_results_table']//*[contains(@class,'sr-hotel__name')]")).size();
        System.out.println(quantity);

    }
}