import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.util.List;

public class LoginPage {
    private static final int TIMEOUT = 30;
    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement button;

    @FindBy(xpath = ".//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    @FindBy(xpath = "(//div[@class='ll-av__container'])[1]")
    private WebElement firstCheckbox;

    @FindBy(xpath = "//span[@title='Спам']")
    private WebElement toSpamButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в спам')]")
    private WebElement spamAlert;

    @FindBy(xpath = "(//button[@title='Пометить флажком'])[1]")
    private WebElement flag;

    @FindBy(xpath = "//button[@title='Пометить флажком']")
    private List<WebElement> flags;

    @FindBy(xpath = "//button[@title='Снять флажок']")
    private WebElement cancelFlag;

    @FindBy(xpath = "//button[@data-title='Снять флажок' or @title='Снять флажок']")
    private List<WebElement> flagedList;

    @FindBy(xpath = "//a[@href='/inbox/']")
    private WebElement inbox;

    @FindBy(xpath = "//a[@href='/spam/']")
    private WebElement spamButton;

    @FindBy(xpath = "//span[@data-title-shortcut='Shift+J']")
    private WebElement notSpamButton;

    @FindBy(xpath = "//*[@id='app-canvas']/div/div[1]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div[2]/a")
    private WebElement clearButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в папку')]")
    private WebElement toFolderAlert;

    @FindBy(xpath = "//span[contains(text(),'Написать письмо')]")
    private WebElement writeButton;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div" +
            "/div[1]/div/div[2]/div/div/label/div/div/input")
    private WebElement emailInput;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendButton;

    @FindBy(xpath = "/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]/span")
    private WebElement sendAlert;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]" +
            "/div/div/div[2]/div[1]/div[1]")
    private WebElement message;

    public LoginPage(WebDriver webDriver) {
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

    public void chooseAndMoveToSpam(WebDriver webDriver) {
        expectClickableAndClick(webDriver, firstCheckbox);
        expectClickableAndClick(webDriver, toSpamButton);
    }

    public void markByFlag(WebDriver webDriver, int numberOfMessages) {
        for (int i = 0; i <= numberOfMessages; i++) {
            expectClickableAndClick(webDriver, flag);
            expectVisibilityOfAllElements(webDriver, flagedList);
        }
    }

    public void cancelMarkByhFlag(WebDriver webDriver) {
        for (int i = flagedList.size(); i > 0; i--) {
            expectVisibilityOfAllElements(webDriver, flagedList);
            expectClickableAndClick(webDriver, cancelFlag);
            expectVisibilityOfAllElements(webDriver, flags);
        }
    }

    public boolean toSpamAlertPresents(WebDriver webDriver) {
        return expectVisibilityAndCheck(webDriver, spamAlert);
    }

    public boolean flagsIsPresent(WebDriver webDriver, int numberOfFlags) {
        expectVisibilityOfAllElements(webDriver, flagedList);
        return (flagedList.size() == numberOfFlags);
    }

    public boolean flagsIsNotPresent(WebDriver webDriver) {
        expectVisibilityOfAllElements(webDriver, flags);
        return flagedList.isEmpty();
    }

    public boolean expectVisibilityAndCheck(WebDriver webDriver, WebElement webElement) {
        return (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectVisibilityOfAllElements(WebDriver webDriver, List<WebElement> list) {
        (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }

    public void returnFromSpam(WebDriver webDriver) {
        expectClickableAndClick(webDriver, spamButton);
        expectVisibility(webDriver, clearButton);
        expectClickableAndClick(webDriver, firstCheckbox);
        expectClickableAndClick(webDriver, notSpamButton);
    }

    public boolean returnFromSpamAlertIsPresents(WebDriver webDriver) {
        return CheckVisibility(webDriver, toFolderAlert);
    }

    public boolean CheckVisibility(WebDriver webDriver, WebElement webElement) {
        return (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectVisibility(WebDriver webDriver, WebElement webElement) {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void writeAndSend(WebDriver webDriver, String emails, String text) {
        expectClickableAndClick(webDriver, writeButton);
        expectClickable(webDriver, emailInput);
        emailInput.sendKeys(emails);
        message.sendKeys(text);
        sendButton.click();
    }

    public boolean sendAlert(WebDriver webDriver) {
        return expectVisibilityAndCheck(webDriver, sendAlert);
    }

    public void expectClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }

}