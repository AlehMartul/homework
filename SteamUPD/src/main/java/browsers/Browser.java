package browsers;

import org.openqa.selenium.WebDriver;


public class Browser {
    private static WebDriver driver;

    public Browser() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = BrowserFactory.choseBrowser();
        }
        return driver;
    }

    public void getMainUrl(String url){
        getDriver().get(url);
    }

    public void fullScreenMode(){
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}