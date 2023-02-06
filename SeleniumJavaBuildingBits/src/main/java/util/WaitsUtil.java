package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static util.Const.POLLING_INTERVAL;
import static util.Const.WAIT_TIME;


public class WaitsUtil {

    //explicit private constructor
    private WaitsUtil() {}

    //function that checks if the element is visible on the page
    public static void waitForElementVisibility(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.visibilityOf(element));
    }

    //wait until the page and all sub-resources have finished loading
    public static void waitForPageToLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(d->((JavascriptExecutor)d).executeScript(
                        "return document.readyState").equals("complete"));
    }

    //wait until the current page has a specific URL
    public static void waitForURLToBePresent(WebDriver driver,String url) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.urlToBe(url));
    }

    //function that checks if the element is clickable
    public static void waitUntilElementIsClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    //wait until an element has a specified attribute and the value is as specified
    public static void waitUntilAttributeValueIsCorrect(WebDriver driver,
                                                        WebElement element,
                                                        String attribute,
                                                        String value) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    //wait until an element has a specific attribute with a specific value
    public static void waitUntilAttributeValueContains(WebDriver driver,
                                                       WebElement element,
                                                       String attribute,
                                                       String value) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    //wait until a specified attributes value is not empty
    public static void waitUntilAttributeIsNotEmpty(WebDriver driver, WebElement element, String attribute) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    //wait until a specified attributes value is not empty
    public static void waitUntilElementIsDisabled(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !element.isEnabled();
                    }
                });
    }

    //wait until a specified element contains specified text
    public static void waitUntilTextIsPresent(WebDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            String elementText = element.getText().toLowerCase();
                            return elementText.contains(text.toLowerCase());

                        } catch (StaleElementReferenceException e) {
                            return null;
                        }
                    }
                });
    }

    //wait until an element becomes invisible
    public static void waitUntilInvisibilityOfElement(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitUntilSelectIsPopulatedWithOptions(WebDriver driver, Select select) {
        new WebDriverWait(driver, Duration.ofMillis(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .until((ExpectedCondition<Boolean>) driver1 -> select.getOptions().size() > 0);
    }

    //stop the thread for specified amount of time
    public static void hardWait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

