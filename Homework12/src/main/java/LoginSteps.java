import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "traktal83";
    private static final String PASSWORD = "oleg86";
    private LoginPage loginPage;
    private WebDriver driver;

    public LoginSteps() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chrome79/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Given("^I am on main page$")
    public void loadMainPage() {
        driver.get(MAIN_URL);
    }

    @When("^I login to e-mail$")
    public void loginToEmail() {
        loginPage.loginToEmail(LOGIN, PASSWORD, driver);
    }

    @Then("^I see logout link$")
    public void logoutLinkPresents() {
        Assert.assertTrue(loginPage.logoutLinkPresents(driver));
    }

    @When("^I add letter to spam$")
    public void chooseAndMoveToSpam() {
        loginPage.chooseAndMoveToSpam(driver);
    }

    @Then("^I see spam alert$")
    public void toSpamAlertPresents() {
        Assert.assertTrue(loginPage.toSpamAlertPresents(driver));
    }

    @When("^I return letter from spam$")
    public void returnFromSpam() {
        loginPage.returnFromSpam(driver);
    }

    @Then("^I see return from spam alert$")
    public void returnFromSpamAlertIsPresents() {

        Assert.assertTrue(loginPage.returnFromSpamAlertIsPresents(driver));
    }

    @When("^I mark (-?\\d+) letters$")
    public void markByFlag() {
        loginPage.markByFlag(driver, 3);
    }

    @Then("^I see selected (-?\\d+) letters are marked$")
    public void lettersAreFlagged(int numberOfMessages) {
        Assert.assertTrue(loginPage.flagsIsPresent(driver, numberOfMessages));
    }

    @When("^I deflag all letters$")
    public void cancelMarkByhFlag() {
        loginPage.cancelMarkByhFlag(driver);
    }

    @Then("^I see all letters are deflaged$")
    public void allLettersAreDeflaged() {

        Assert.assertTrue(loginPage.flagsIsNotPresent(driver));
    }

    @When("^I send letter for group$")
    public void writeAndSend() {
        String adresses = "martul@palladim.ru, dark-tower2007@yandex.ru";
        String text = "This is testing letter";
        loginPage.writeAndSend(driver, adresses, text);
    }

    @Then("^I see alert that letters are sent$")
    public void sendAlert() {
        Assert.assertTrue(loginPage.sendAlert(driver));
    }

    @After
    public void afterClass() {
        driver.quit();
    }
}