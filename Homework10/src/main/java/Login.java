import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private static final int TIMEOUT = 20;
    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement button;

    @FindBy(xpath = ".//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    public Login(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void loginToEmail(String login, String password, WebDriver webDriver) {
        loginField.clear();
        loginField.sendKeys(login);
        clickButton();
        passwordField.clear();
        passwordField.sendKeys(password);
        clickButton();
    }

    public void clickButton() {
        button.click();
    }

    public boolean logoutLinkPresents(WebDriver webDriver) {
        return (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(logoutLink)).isDisplayed();
    }
}
