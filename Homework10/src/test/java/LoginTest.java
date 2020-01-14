import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private Login login;
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        login = new Login(driver);
    }
    @Test
public void LoginTest(){
        login.loginToEmail("traktal83", "oleg86", driver);
        Assert.assertTrue(login.logoutLinkPresents(driver));
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
