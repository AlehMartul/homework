import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BookingParisTest {

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
    }

    @Test
    public void findHotel() throws InterruptedException {
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://www.booking.com/");
        int actualDatePlusThreeDays = 3;
        int actualDatePlusTenDays = 10;
        int numberOfDays = 7;
        String rangeOfPrices;
        LocalDate checkIn = LocalDate.now().plusDays(actualDatePlusThreeDays);
        LocalDate checkOut = LocalDate.now().plusDays(actualDatePlusTenDays);
        WebElement search = chromeDriver.findElement(By.xpath(".//input[@name='ss']"));
        search.clear();
        search.sendKeys("Париж");
        WebElement dateComponent = chromeDriver.findElement(By.xpath("//*[contains(@class,'xp__date-time')][1]"));
        dateComponent.click();
        WebElement enterDate = chromeDriver.findElement(By.xpath("//*[@data-date='" + checkIn + "']"));
        enterDate.click();
        Thread.sleep(1000);
        WebElement quitDate = chromeDriver.findElement(By.xpath("//*[@data-date='" + checkOut + "']"));
        quitDate.click();
        Thread.sleep(1000);
        search.submit();
        Thread.sleep(3000);
        rangeOfPrices = chromeDriver.findElement(By.xpath(".//*[@data-id='pri-1']")).getText();
        WebElement cheapHotels = chromeDriver.findElement
                (By.xpath(".//*[@class='bui-checkbox__label filter_item css-checkbox']"));
        cheapHotels.click();
        Thread.sleep(3000);
        int numbersOfCheap = chromeDriver.findElements
                (By.xpath("//*[@id='search_results_table']//*[contains(@class,'sr-hotel__name')]")).size();
        System.out.println("Найдено " + numbersOfCheap + " дешевых отелей");
        WebElement sortByRating = chromeDriver.findElement(By.xpath("//*[@data-category='review_score_and_price']"));
        sortByRating.click();
        Thread.sleep(3000);
        WebElement choosenHotel = chromeDriver.findElement(By.xpath("//*[@class='hotel_name_link url']"));
        choosenHotel.click();
        Thread.sleep(3000);
        for (String handle : chromeDriver.getWindowHandles()) {
            chromeDriver.switchTo().window(handle);
        }
        Select selectNumOfGuests = new Select(chromeDriver.findElement(By.xpath(".//*[@class='hprt-nos-select']")));
        selectNumOfGuests.selectByIndex(2);
        Thread.sleep(2000);
        String totalPrice = chromeDriver.findElement
                (By.xpath("//*[contains(@class,'hprt-reservation-total-price bui-price-display__value')]")).getText();
        rangeOfPrices = rangeOfPrices.replaceAll("[^-?0-9]+", " ");
        totalPrice = totalPrice.replaceAll("[^-?0-9]+", " ");
        List<String> rangeList = Arrays.asList(rangeOfPrices.trim().split(" "));
        List<String> totalList = Arrays.asList(totalPrice.trim().split(" "));
        int range = Integer.parseInt(rangeList.get(2));
        int total = Integer.parseInt(totalList.get(0));
        System.out.println
                (total / numberOfDays <= range ? "Цена в рамках заданного диапозона" : "Цена выше заданного диапозона");
        Assert.assertTrue(total / numberOfDays <= range);
    }
}