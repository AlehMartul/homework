import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendMessageTest {
    private WebDriver driver;
    private SendMessage sendMessage;
    private Login login;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        login = new Login(driver);
        sendMessage = new SendMessage(driver);
        login.loginToEmail("traktal83", "oleg86", driver);
    }

    @Test
    public void returnFromSpamTest() {
        sendMessage.writeAndSend(driver, "dark-tower2007@yandex.ru, oleg_man_utd@tut.by, martul@palladium.ru", "Это автоматическое сообщение");
        Assert.assertTrue(sendMessage.sendAlert(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
