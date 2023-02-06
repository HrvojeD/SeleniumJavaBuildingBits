package util;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Const.*;
import static util.WaitsUtil.hardWait;

public class Helpers {

    public static final Object substringLock = new Object();

    public static String getSubstringUsingRegex(String string, String regex) {

        synchronized (substringLock) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);
            if (matcher.find())
            {
                return matcher.group(1);
            }
            return REGEX_SUBSTRING_NOT_FOUND;
        }
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public static String generateRandomEmail() {
        return generateRandomString() + "@example.com";
    }

    public static void print(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void slowTypeString(String text, WebElement element) {

        //find the middle of the string
        int middleOfString = text.length() / 2;

        //split the string into two parts
        String firstPart = text.substring(0, middleOfString);
        String secondPart = text.substring(middleOfString);

        //send the first part all at once
        element.clear();
        element.sendKeys(firstPart);
        hardWait(TYPING_SPEED);

        //slowly type the second part
        String[] characters = secondPart.split("");
        for (String letter: characters){
            element.sendKeys(letter);
            hardWait(TYPING_SPEED);
        }

        /*
            Sometimes, server validation can be slow and typing can be too fast for it, this is an attempt to mitigate
            that issue. Last letter is deleted and typed again because validation is performed on keystroke,
            and because of that, the server has time to perform validation again and enable
            further sections of the application.
         */
        for (int i = 0; i < 2; i++) {
            element.sendKeys(Keys.BACK_SPACE);
            hardWait(LAST_LETTER_WAIT);
            element.sendKeys(characters[characters.length - 1]);
            hardWait(LAST_LETTER_WAIT);
        }
    }
}
