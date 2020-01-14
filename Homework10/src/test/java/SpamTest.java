import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpamTest {
    private WebDriver driver;
    private Spam spam;
    private Login login;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        login = new Login(driver);
        spam = new Spam(driver);
        login.loginToEmail("traktal83", "oleg86", driver);

    }

    @Test
    public void returnFromSpamTest() {
        spam.returnFromSpam(driver);
        Assert.assertTrue(spam.returnFromSpamAlertIsPresents(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
