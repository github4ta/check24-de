package org.company.novysh;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    public static String getText(WebElement element) {
        if (element == null) {
            return "";
        }
        try {
            return element.getText().strip();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
