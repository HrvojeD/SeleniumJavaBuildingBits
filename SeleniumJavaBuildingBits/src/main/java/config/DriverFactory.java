package config;

import org.openqa.selenium.WebDriver;


public class DriverFactory {

    //explicit private constructor
    private DriverFactory() {}

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public void closeDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }
}
