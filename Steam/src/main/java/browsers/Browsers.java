package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.ArrayList;

public class Browsers {
    private static WebDriver driver;

    public Browsers() {
    }

    public static WebDriver getDriver(String browser) {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            return driver = new ChromeDriver();

        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return driver = new FirefoxDriver();
        }
        throw new IllegalArgumentException("It is not a browser");
    }

    public static void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public void switchTab(int numberOfTab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(numberOfTab));
    }
}