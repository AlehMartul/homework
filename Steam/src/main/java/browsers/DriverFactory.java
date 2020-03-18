package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
    public WebDriver getInstance(String browser) {
        if ("chrome".equals(browser)) {
            return ChromeDriverSingleton.getInstance();
        } else if ("firefox".equals(browser)) {
            return FirefoxDriverSingleton.getInstance();
        } else {
            return ChromeDriverSingleton.getInstance(); //default
        }
    }
}
