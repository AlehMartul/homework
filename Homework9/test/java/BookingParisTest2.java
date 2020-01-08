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

public class BookingParisTest2 {

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
        String totalPrice;
        LocalDate checkIn = LocalDate.now().plusDays(actualDatePlusThreeDays);
        LocalDate checkOut = LocalDate.now().plusDays(actualDatePlusTenDays);
        WebElement search = chromeDriver.findElement(By.xpath(".//input[@name='ss']"));
        search.clear();
        search.sendKeys("Париж");
        chromeDriver.findElement(By.xpath("//*[@id='xp__guests__toggle']")).click();
        WebElement enterNumOfVisitors = chromeDriver.findElement
                (By.xpath(".//*[@class='bui-button bui-button--secondary bui-stepper__add-button ']"));
        enterNumOfVisitors.click();
        enterNumOfVisitors.click();
        WebElement enterNumOfRooms = chromeDriver.findElement
                (By.xpath(".//*[@aria-label='Номера: увеличить количество']"));
        enterNumOfRooms.click();
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
        rangeOfPrices = chromeDriver.findElement(By.xpath(".//*[@data-id='pri-5']")).getText();
        WebElement expensiveHotels = chromeDriver.findElement(By.xpath(".//*[@data-id='pri-5']"));
        expensiveHotels.click();
        Thread.sleep(3000);
        WebElement sortByRating = chromeDriver.findElement(By.xpath(".//*[@data-category='price']"));
        sortByRating.click();
        Thread.sleep(3000);
        WebElement choosenHotel = chromeDriver.findElement(By.xpath("//*[@class='hotel_name_link url']"));
        choosenHotel.click();
        Thread.sleep(3000);
        for (String handle : chromeDriver.getWindowHandles()) {
            chromeDriver.switchTo().window(handle);
        }
        WebElement book = chromeDriver.findElement(By.xpath(".//*[@class='submitButton']"));
        book.click();
        Thread.sleep(2000);
        totalPrice = chromeDriver.findElement
                (By.xpath(".//*[contains(@class,'bui-price-display__value prco-inline')]")).getText();
        WebElement comfirm = chromeDriver.findElement(By.xpath(".//*[@class='hprt-reservation-cta']"));
        comfirm.click();
        Thread.sleep(10000);
        WebElement alert = chromeDriver.findElement(By.xpath(".//*[@class='modal-mask-closeBtn bp_rlu_footer_close']"));
        alert.click();
        WebElement lastname = chromeDriver.findElement(By.xpath(".//*[@name='lastname']"));
        lastname.sendKeys("Smirnov");
        WebElement email = chromeDriver.findElement(By.xpath(".//*[@name='email']"));
        email.sendKeys("traktal83@mail.ru");
        WebElement emailConfirm = chromeDriver.findElement(By.xpath(".//*[@name='email_confirm']"));
        emailConfirm.sendKeys("traktal83@mail.ru");
        WebElement bookButton = chromeDriver.findElement(By.xpath(" .//*[@name='book']"));
        bookButton.click();
        Thread.sleep(10000);
        WebElement alert2 = chromeDriver.findElement(By.xpath(".//*[@class='modal-mask-closeBtn bp_rlu_footer_close']"));
        alert2.click();
        WebElement adress = chromeDriver.findElement(By.xpath(".//*[@name='address1']"));
        adress.sendKeys("Vaneeva 8-68");
        WebElement city = chromeDriver.findElement(By.xpath(".//*[@name='city']"));
        city.sendKeys("Minsk");
        WebElement phone = chromeDriver.findElement(By.xpath(".//*[@id='phone']"));
        phone.sendKeys("292020327");
        Select selectTypeOfCard = new Select(chromeDriver.findElement(By.xpath(".//*[@id='cc_type']")));
        selectTypeOfCard.selectByIndex(3);
        Thread.sleep(2000);
        WebElement cardNumber = chromeDriver.findElement(By.xpath(".//*[@name='cc_number']"));
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        Thread.sleep(500);
        cardNumber.sendKeys("42");
        WebElement confirm = chromeDriver.findElement
                (By.xpath(".//*[contains(@class, 'js-book_and_cancel-continue_btn')]"));
        confirm.click();
        Thread.sleep(15000);
        WebElement alert3 = chromeDriver.findElement(By.xpath(".//*[@class='modal-mask-closeBtn']"));
        alert3.click();
        Thread.sleep(2000);
        String errorMessage = chromeDriver.findElement(By.xpath(".//*[@class='bui-alert__title']")).getText();
        rangeOfPrices = rangeOfPrices.replaceAll("[^-?0-9]+", " ");
        totalPrice = totalPrice.replaceAll("[^-?0-9]+", " ");
        List<String> rangeList = Arrays.asList(rangeOfPrices.trim().split(" "));
        List<String> totalList = Arrays.asList(totalPrice.trim().split(" "));
        int range = Integer.parseInt(rangeList.get(0));
        int total = Integer.parseInt(totalList.get(0));
        Assert.assertTrue(errorMessage == "Похоже, вы кое-что забыли. Пожалуйста, заполните эти поля, чтобы продолжить:");
        System.out.println
                (total / numberOfDays < range ? "Цена в рамках заданного диапозона" : "Цена ниже заданного диапозона");
    }
}