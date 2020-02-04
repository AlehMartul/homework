package singletonWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonWebDriver {
    private static WebDriver driver;

    private SingletonWebDriver() {
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/Selenium/chrome79/chromedriver.exe");
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver !=null) {
            driver.quit();
            driver = null;
        }
    }
}