package de.check24.ui.driver;

import de.check24.config.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Driver {
    private static final Duration DEFAULT_WAIT_SECONDS = Duration.ofSeconds(10);
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigLoader.get("browser.type", "chrome");
            driver = DriverFactory.createDriver(browserType);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            DriverFactory.quitDriver(driver);
            driver = null;
        }
    }

    public static void navigateTo(String url) {
        getDriver().get(url);
    }

    public static String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public static int getQuantityOfElements(String locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        return elements.size();
    }

    public static void click(String locator) {
        getDriver().findElement(By.xpath(locator)).click();
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void fill(String locator, String value) {
        getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public static String getText(String locator) {
        return getDriver().findElement(By.xpath(locator)).getText();
    }

    public static void waitAndClick(String locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        element.click();
    }

    public static void waitAndFill(String locator, String value) {
        // можно дописать логику ожидания при необходимости
        getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public static String waitAndGetText(String locator) {
        // можно дописать логику ожидания при необходимости
        return getDriver().findElement(By.xpath(locator)).getText();
    }

    public static boolean isElementDisplayedWithWait(String locator, int seconds) {
        return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)))
                .isDisplayed();
    }

    public static WebElement getElementInShadowRoot(String hostLocator, String cssSelector) {
          return getWait(10)
                  .ignoring(NoSuchElementException.class)
                  .until(d -> {
                WebElement element = d.findElement(By.xpath(hostLocator))
                        .getShadowRoot()
                        .findElement(By.cssSelector(cssSelector));
                return element.isDisplayed() ? element : null;
          });
    }

    // далее можно дописывать любые необходимые методы (инструменты) для взаимодействия с браузером,
    // вкладками, страницей и элементами


}
