package util;

import com.aventstack.extentreports.Status;
import config.ExtentReportFactory;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static util.Const.*;
import static util.WaitsUtil.hardWait;

public class RecursiveClickUtil {

    int recursionCounter = 0;

    /*
        Attempt to recursively click on an element.
     */
    public void clickRecursively(WebElement element) {

        if (recursionCounter >= RECURSIVE_CLICK_COUNTER) {
            ExtentReportFactory.getInstance().getExtentTest().log(Status.FAIL, ELEMENT_NOT_CLICKABLE);
            Assert.fail(ELEMENT_NOT_CLICKABLE);
        }

        try {
            element.click();

        } catch (ElementClickInterceptedException
                 | StaleElementReferenceException
                 | NoSuchElementException e) {
            hardWait(RECURSION_WAIT);
            recursionCounter++;
            clickRecursively(element);
        }
    }
}
