import singletonWebDriver.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import mailBox.LoginPage;
import mailBox.Spam;

public class SpamTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private Spam spam;

    @BeforeClass
    public void beforeClass() {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        spam = new Spam();
    }

    @Test
    public void returnFromSpamTest() {
        loginPage.loadMainPage();
        loginPage.loginAsCorrectUser();
        spam.returnMessageFromSpam();
        Assert.assertTrue(spam.returnFromSpamAlertIsPresents());
    }

    @AfterClass
    public void afterClass() {
        SingletonWebDriver.quitDriver();
    }
}