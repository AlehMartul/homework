package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverSingleton {
    private static WebDriver instance;

    private FirefoxDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (instance == null) {
            WebDriverManager.firefoxdriver().setup();
            instance = new FirefoxDriver();
            return instance;
        }
        return instance;
    }
}
