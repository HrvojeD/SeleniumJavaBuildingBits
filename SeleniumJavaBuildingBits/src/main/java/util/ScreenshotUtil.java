package util;

import config.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenshotUtil {

    public String takeScreenshot() {

        File screenshotFile = ((TakesScreenshot) DriverFactory
                .getInstance()
                .getDriver())
                .getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir")
                + "/target/reportScreenshots/"
                + LocalDateTime.now().toString().replaceAll(":","_")
                + ".png";

        File screenshot = new File(screenshotPath);

        try {
            FileUtils.copyFile(screenshotFile, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
