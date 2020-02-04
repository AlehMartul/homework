import singletonWebDriver.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import mailBox.LoginPage;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void createDriver() {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        loginPage.loadMainPage();
    }

    @Test
    public void authorizationWithRigthParameters() {
        loginPage.loginAsCorrectUser();
        Assert.assertTrue(loginPage.logoutLinkPresents());
    }

    @AfterMethod
    public void driverQuit() {
        SingletonWebDriver.quitDriver();
    }
}