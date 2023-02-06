package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public WebDriver createBrowserInstance(String browser) {

        WebDriver driver = null;

        switch (browser) {

            case "local_chrome" -> {
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                options.addArguments("--incognito");
                options.addArguments("--log-level=3");
                options.addArguments("window-size=1920,1080");
                options.addArguments("--headless");

                driver = new ChromeDriver(options);
            }

            case "local_firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logfile");
                WebDriverManager.firefoxdriver().setup();
                options.addArguments("--private");
                options.addArguments("--headless");

                driver = new FirefoxDriver(options);
            }
        }
        return driver;
    }
}
