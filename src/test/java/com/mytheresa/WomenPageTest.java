package com.mytheresa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WomenPageTest {
    private String WOMEN_PAGE_URL = "https://www.mytheresa.com/int/en/women";
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String categories = "//span[@class='nav__item__link__label']";
    private String wishlistIcon = "//span[@class='icon__wishlist']";
    private String actualScreenshotPath = "target/screenshots/actual_wl_women.png";
    private String expectedScreenshotPath = "target/screenshots/expected_wl_women.png";

    @Test
    @DisplayName("UI-TC-020: Verify category titles on the Women's page")
    public void verifyCategoryTitles() {
        WebDriver driver = initDriver();
        acceptCookies(driver);

        List<String> expectedTitles = List.of("new arrivals", "designers", "clothing", "shoes", "bags", "accessories", "jewelry", "vacation shop", "sale");
        List<String> actualTitles = getTitles(categories, driver);

        Assertions.assertEquals(expectedTitles, actualTitles);

        tearDown(driver);
    }

    @Test
    @DisplayName("UI-TC-022: Verify wishlist icon")
    public void verifyWishlistIcon() throws IOException {
        WebDriver driver = initDriver();

        takeScreenshot(driver, wishlistIcon, actualScreenshotPath);
        Assertions.assertFalse(checkDifference(actualScreenshotPath, expectedScreenshotPath));

        tearDown(driver);
    }

    private List<String> getTitles(String categories, WebDriver driver) {
        List<WebElement> listOfElements = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(categories)));
        List<String> listOfTitles = new ArrayList<>();
        for (WebElement element : listOfElements) {
            listOfTitles.add(element.getText().toLowerCase());
        }
        return listOfTitles;
    }

    private void takeScreenshot(WebDriver driver, String element, String actualScreenshotPath) throws IOException {
        WebElement el = driver.findElement(By.xpath(element));
        File source = el.getScreenshotAs(OutputType.FILE);
        File destination = new File(actualScreenshotPath);
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean checkDifference(String actualScreenshotPath, String expectedScreenshotPath) throws IOException {
        BufferedImage expected = ImageIO.read(new File(expectedScreenshotPath));
        BufferedImage actual = ImageIO.read(new File(actualScreenshotPath));
        ImageDiff diff = new ImageDiffer().makeDiff(expected, actual);
        return diff.hasDiff();
    }

    private WebDriver initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(WOMEN_PAGE_URL);
        return driver;
    }

    private void acceptCookies(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(shadowHostLocator)))
                .getShadowRoot()
                .findElement(By.cssSelector(acceptCookiesButton))
                .click();
    }

    private void tearDown(WebDriver driver) {
        driver.quit();
    }
}
