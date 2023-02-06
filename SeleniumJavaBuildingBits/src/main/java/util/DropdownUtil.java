package util;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Const.RECURSION_WAIT;
import static util.WaitsUtil.*;

public class DropdownUtil {

    int recursionCounter = 0;

    /**
     *
     * @param driver Webdriver object
     * @param dropdownElement Root element of all list items
     * @param valueToBeSelected String that is to be selected from the list
     * @param byLocatorOfDropboxItems Shared locator of all items in a list
     */
    public void recursivelySelectFromDropdown(WebDriver driver,
                                              WebElement dropdownElement,
                                              String valueToBeSelected,
                                              By byLocatorOfDropboxItems) {
        try {
            selectFromDropdown(driver, dropdownElement, valueToBeSelected, byLocatorOfDropboxItems);
            hardWait(RECURSION_WAIT);
        } catch (StaleElementReferenceException e) {
            hardWait(RECURSION_WAIT);
            recursionCounter++;
            recursivelySelectFromDropdown(driver, dropdownElement, valueToBeSelected, byLocatorOfDropboxItems);
        }
    }

    private void selectFromDropdown(WebDriver driver,
                                    WebElement element,
                                    String valueToBeSelected,
                                    By locator){

        waitForElementVisibility(driver, element);
        waitUntilElementIsClickable(driver, element);

        List<WebElement> dropdownElements = driver.findElements(locator);

        for (WebElement elementOne : dropdownElements) {
            if (elementOne.getText().equalsIgnoreCase(valueToBeSelected)) {
                waitForElementVisibility(driver, elementOne);
                waitUntilElementIsClickable(driver, elementOne);
                elementOne.click();
                break;
            }
        }
    }
}
