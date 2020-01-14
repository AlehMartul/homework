import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MoveToSpamTest {
    private WebDriver driver;
    private MoveToSpam moveToSpam;
    private Login login;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        moveToSpam = new MoveToSpam(driver);
        login = new Login(driver);
        login.loginToEmail("traktal83", "oleg86", driver);
    }

    @Test
    public void chooseAndMoveToSpamTest() {
        moveToSpam.chooseAndMoveToSpam(driver);
        Assert.assertTrue(moveToSpam.toSpamAlertPresents(driver));
    }

    @Test (dependsOnMethods = "chooseAndMoveToSpamTest")
    public void markByFlagTest() {
        moveToSpam.markByFlag(driver, 3);
        Assert.assertTrue(moveToSpam.flagsIsPresent(driver, 3));
    }

    @Test (dependsOnMethods = "markByFlagTest")
    public void cancelMarkByhFlag() {
        moveToSpam.cancelMarkByhFlag(driver);
        Assert.assertTrue(moveToSpam.flagsIsNotPresent(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
