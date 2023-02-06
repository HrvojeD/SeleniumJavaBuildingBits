package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorUtil {

    private final WebDriver driver;

    public JavaScriptExecutorUtil(WebDriver driver){
        this.driver = driver;
    }

    public void clickElementUsingJavaScript(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(
                "arguments[0].click()",element);
    }

    public String getTextByIdUsingJavascript(String elementId) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript(
                "return document.getElementById('" + elementId + "').value").toString();
    }
}
