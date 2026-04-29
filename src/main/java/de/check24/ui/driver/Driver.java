package de.check24.ui.driver;

import de.check24.config.ConfigLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;

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

    public static String getElementAttribute(String locator, String attributeName) {
            WebElement element = getWait(5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            String value = element.getAttribute(attributeName);
            return (value != null) ? value : "";
    }

    public static boolean isAttributeValueEqualsToExpected(String locator, String attributeName, String expectedValue) {
        String actualValue = getElementAttribute(locator, attributeName);
        if (actualValue.isEmpty() && !expectedValue.isEmpty()) {
            return false;
        }
        return actualValue.equals(expectedValue);
    }

    public static void waitAndClearAndFillAndPressEnter(String locator, String value) {
        WebElement element = getWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
    }

    public static List<String> getTexts(String locator) {
        return getWait(10)
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    List<WebElement> elements = d.findElements(By.xpath(locator));
                    return elements.stream()
                            .map(WebElement::getText)
                            .filter(text -> text != null && !text.isBlank())
                            .toList();
                });
    }

    public static List<WebElement> getElementList(String locator) {
        getWait(10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        return getDriver().findElements(By.xpath(locator));
    }

    public static List<Double> getPrices(String locator) {
        getWait(10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        return getWait(10)
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    List<WebElement> elements = d.findElements(By.xpath(locator));
                    return elements.stream()
                            .map(el -> el.getAttribute("innerText"))
                            .filter(text -> text != null && !text.trim().isEmpty())
                            .map(text -> {
                                String cleaned = text.trim();
                                if (cleaned.contains(".") && cleaned.contains(",")) {
                                    cleaned = cleaned.replace(".", "");
                                }
                                cleaned = cleaned.replace(",", ".");
                                return cleaned.replaceAll("[^0-9.]", "");
                            })
                            .map(Double::parseDouble)
                            .toList();
                });
    }

    public static void scrollSliderToCenter(String locator) {
        WebElement slider = getWait(10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        Actions action = new Actions(getDriver());
        action.clickAndHold(slider).moveByOffset(10, 0).release().build().perform();
    }

    public static void scrollScreenToTheEnd() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static boolean isAttributeInChosenDiapazon(String minPriceLocator,String maxPriceLocator,String priceLocator) {
        String minText = getDriver().findElement(By.xpath(minPriceLocator)).getText().replaceAll("[^0-9.]", "");
        String maxText = getDriver().findElement(By.xpath(maxPriceLocator)).getText().replaceAll("[^0-9.]", "");
        double minPrice = Double.parseDouble(minText);
        double maxPrice = Double.parseDouble(maxText);
        List<Double> prices = getPrices(priceLocator);
        return prices.stream().allMatch(p -> p >= minPrice && p <= maxPrice);
    }
}
