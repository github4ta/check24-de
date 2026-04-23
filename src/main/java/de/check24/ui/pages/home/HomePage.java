package de.check24.ui.pages.home;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static de.check24.ui.pages.home.HomeLocator.*;
import static de.check24.ui.pages.home.HomeText.*;

/**
 * Page Object for Check24 HomePage
 */
public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By logo = By.cssSelector("a[data-testid='header-logo']");
    private final By logoCheckInHeader = By.xpath("//a[@class='c24-logo']");
    private final By searchInput = By.cssSelector("input[data-testid='search-input']");
    private final By searchButton = By.cssSelector("button[data-testid='search-button']");
    private final By copyrightText = By.xpath("//*[contains(text(),'© 2026 CHECK24 Vergleichsportal GmbH München')]");
    private final By anyCopyright2026 = By.xpath("//*[contains(text(),'2026') and contains(text(),'CHECK24')]");
    private final By facebookButton = By.xpath("//*[@id=\"c24-footer\"]/div[2]/div[2]/a[1]");
    private final By cookieAcceptButton = By.xpath("//a[text()='geht klar']");
    private final By loginIcon = By.xpath("//a[@class='c24-customer-hover-wrapper c24-login-opener']");
    private final By enterEmail = By.xpath("//*[@id=\"cl_login\"]");
    private final By forgotPassword = By.xpath("//*[@id=\"c24-content\"]/div/div/div/div/unified-login//div/div/div[2]/form/div[2]/div[1]/div/a/div/div[1]/font/font");
    private final By AGBlink = By.xpath("//*[@id=\"c24-footer\"]/div[2]/div[1]/div[2]/a[1]");
    private final By searchHotelInput = By.xpath("//input[@id='id-search-form-destination']");
    private final By searchParisHotelsButton = By.xpath("//*[@id=\"serp\"]/div/div/div[1]/div[3]/div/div/div/div/div[4]/button");
    private final By sortingByPopularityInDescendingOrder = By.xpath("//span[text()='Beliebtheit']");
    private final By sortingByPriceAscending = By.xpath("//*[@id=\":r0:\"]/div/div/div/div[5]/span");
    private final By popupSplashScreenSpringDealContainer = By.xpath("//*[@id='splashScreenContainer']//div[contains(@class, 'close')]\n");
    private final By hotelButton = By.xpath("//*[@id=\"c24-quickchips\"]/div/div/a[1]");
    private final By reiseButton = By.xpath("//*[@id=\"travelToggleContainer\"]/div/div/div[1]/button/div[2]");
    private final By beliebteSportsSection = By.xpath("//*[@id='c24-container-18']/div[5]/div[1]/h5");
    private final By wandernBlock = By.xpath("//*[@id='c24-container-18']/div[5]/div[2]/a[2]/div[2]/div[1]");
    private final By funchalMadeiraLink = By.xpath("//*[@id='c24-indi-page-container-content']/div[1]/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/a/div");
    private final By datenschutzLink = By.xpath("//a[@title='Datenschutz']");
    private final By searchBar = By.xpath("//*[@id=\"c24-search-header\"]");
    private final By parisHotelSuggestion = By.xpath("//*[@id=\"serp\"]/div/div/div[2]/div[1]");
    private final By impressumLink = By.xpath("//a[@title='Impressum']");
    private final By sportWander = By.xpath("//a[@href='https://individualreisen.check24.de/wandern?tid=widget']");
    private final By titleWander =  By.xpath("//h1");
    private final By searchBtn = By.xpath("//div[@class='c24-search-button']");
    private final By parisHotelsTitle = By.xpath("//div[@class='travel-widget__form-title travel-widget__form-title--desktop new']");
    private final By entfernungFestlegenToggle = By.xpath("//button[@type='button' and contains(@class, 'slideToggle')]");
    private final By sectionPopularDestinationsForVacationRentals = By.xpath("//*[@id='c24-container-18']/div[6]/div[2]/a[4]/div[1]");

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,TIMEOUT);
        js = (JavascriptExecutor) driver;
    }

    public void navigateToHomePage() {
        driver.get("https://www.check24.de/");
    }

    public String getCopyrightText() {
        return driver.findElement(COPYRIGHT_FOOTER).getText();
    }

    public boolean isCopyrightFooterTextCorrect() {
        return getCopyrightText().contains(COPYRIGHT_FOOTER_TEXT);
    }

    public void search(String searchTerm) {
        try {
            WebElement input = driver.findElement(SEARCH_IN_HEADER);
            input.click();
            input.clear();
            input.sendKeys(searchTerm);
            driver.findElement(searchBtn).click();
        } catch (Exception e) {
            throw new RuntimeException("Search functionality failed: " + e.getMessage());
        }
    }

    public String getUrl() {
        return getUrlAsUTF8String();
    }

    private String getUrlAsUTF8String() {
        return URLDecoder.decode(driver.getCurrentUrl(), StandardCharsets.UTF_8);
    }

    private void scrollByJavascriptExecutor(int pixels){
        js.executeScript(String.format("window.scrollBy(0, %d)", pixels));
    }

    private void scrollDown(int px) {
        js.executeScript("window.scrollBy(0, " + px + ")");
    }

    public void clickSectionTurkey() {
        scrollDown(1000);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(SECTION_TURKEY));
        element.click();
    }

    public void clickLogoCheckInHeader() {
        driver.findElement(LOGIN_CHECK_IN_HEADER).click();
    }

    public boolean isHomepageUrl() {
        return HOMEPAGE_URL.equals(driver.getCurrentUrl());
    }

    public boolean isHotelPageUrl() {
        return HOTEL_PAGE_URL.equals(driver.getCurrentUrl());
    }

    public void clickLinkFerienwohnung() {
        driver.findElement(LINK_FERIENWOHNUNG).click();
    }

    public void clickToSearchFieldInHeader() {
        driver.findElement(SEARCH_IN_HEADER).click();
    }

    public void fillInputInSearchHeader(String value) {
        driver.findElement(SEARCH_IN_HEADER).sendKeys(value);
    }

    public void submitSearchByEnter() {
        driver.findElement(SEARCH_IN_HEADER).sendKeys(Keys.ENTER);
    }

    public String getTextAfterSearchWarsaw() {
       return driver.findElement(LABEL_WARSCHAU).getText().replace("\"", "").trim();
    }

    public void clickFacebookButton() {
        driver.findElement(facebookButton).click();
    }

    public String getFacebookPageUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void clickLoginIcon() {
        driver.findElement(loginIcon).click();
    }

    public void enterEmail() {
        driver.findElement(enterEmail).click();
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }

    public void clickAGBlink() {
        driver.findElement(AGBlink).click();
    }

    public String getSearchHotelInputPlaceholder() {
        try {
            WebElement input = driver.findElement(searchHotelInput);
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            return "";
        }
    }

    public void clickSearchBar() {
        driver.findElement(searchBar).click();
    }

    public void sendKeysSearchBar() {driver.findElement(searchBar).sendKeys("Paris");}

    public void clickParisHotelSuggestion() {driver.findElement(parisHotelSuggestion);}

    public String getParisHotelCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPersonalAccountButtonDisplayed() {
        return driver.findElement(PERSONAL_ACCOUNT_BUTTON).isDisplayed();
    }

    private boolean elementIsNotNull(WebElement elem){
        return elem != null;
    }
    private boolean isElementClickable(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return elementIsNotNull(element);
    }

    public boolean isAGBLinkClickable() {
        return isElementClickable(AGB_LINK);
    }

    public int getSocialIconCount() {
        return driver.findElements(SOCIAL_ICON).size();
    }

    private void moveToElementAndClickWithPause(WebElement element, int millis) {
        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofMillis(millis))
                .click()
                .perform();
    }

    public void clickToSearchFieldInHeaderUsingActions() {
        WebElement element = driver.findElement(SEARCH_IN_HEADER);
        moveToElementAndClickWithPause(element,400);
    }

    public void fillInputInSearchHeaderUsingActions(String value) {
        WebElement input = driver.findElement(SEARCH_IN_HEADER);
        Actions actions = new Actions(driver);
        Random random = new Random();

        actions.moveToElement(input).click().perform();
        for (char ch : value.toCharArray()) {
            actions.sendKeys(String.valueOf(ch))
                    .pause(Duration.ofMillis(100 + random.nextInt(200)))
                    .perform();
        }
    }

    public void clickToSearchParisHotelsButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(searchParisHotelsButton));
        moveToElementAndClickWithPause(button,500);
    }

    public void clickOnPopupWindowCross() {
        WebElement cross = wait.until(ExpectedConditions.elementToBeClickable(popupSplashScreenSpringDealContainer));
        moveToElementAndClickWithPause(cross,500);
    }

    public void clickOnSortingField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By locator = By.xpath("//div[contains(@class, 'sorting')]//span/div");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).scrollToElement(element).perform();
        moveToElementAndClickWithPause(element,500);
    }

    public void clickOnSortingByPopularityInDescendingOrder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(sortingByPopularityInDescendingOrder)).click();
    }

    /*Select sorting by price method*/
    public void selectSortingByPriceAscending() {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(sortingByPriceAscending));
        option.click();
    }

    public boolean isSortingByPopularityInDescendingOrderFilterWorking() {
        List<WebElement> hotels = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@data-test-id-qa=\"static-results-list-result\"]"))
        );
        if(hotels.size()>2){
            String ratingText1 = hotels.get(0).findElement(By.xpath(".//*[@data-test-id-qa=\"hotel-rating\"]")).getText();
            String ratingText2 = hotels.get(1).findElement(By.xpath(".//*[@data-test-id-qa=\"hotel-rating\"]")).getText();
            double rating1 = Double.parseDouble(ratingText1.replaceAll("[^0-9.]", ""));
            double rating2 = Double.parseDouble(ratingText2.replaceAll("[^0-9.]", ""));
            return rating1 >= rating2;
        }
        return false;
    }

    /*Check sorting by price method*/
    public  boolean isSortingByPriceAscendingFilterWorking() {
        List<WebElement> hotels = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@data-test-id-qa,'results-list') and contains(@data-test-id-qa,'result')]"))
        );
        if (hotels.size() < 2) {
            return false;
        }
        double previousPrice = extractPrice(hotels.get(0));
        for (int i = 1; i < hotels.size(); i++) {
            double currentPrice = extractPrice(hotels.get(i));
            if (currentPrice < previousPrice){
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }

    /*helper for checkIsSortingByPriceAscending method*/
    public double extractPrice(WebElement hotel) {
        String priceText = hotel.findElement(By.xpath("//*[data-test-id-qa='results-list-price']")
        ).getText();
        return Double.parseDouble(priceText.replaceAll("[^0-9]", ""));
    }

    public void clickHotelButton() {
        driver.findElement(hotelButton).click();
    }

    public void clickReiseButton() {
        driver.findElement(reiseButton).click();
    }

    public void openBeliebteSportsSection() {WebElement section = driver.findElement(beliebteSportsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
        driver.findElement(wandernBlock).click();
    }

    public void clickFunchalSpot() {
        driver.findElement(funchalMadeiraLink).click();
    }

    public void clickDatenschutzLink() {
        WebElement element = driver.findElement(datenschutzLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickImpressumLink() {
        WebElement element = driver.findElement(impressumLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickWander() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,2500)");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sportWander)).click();
    }

    public String getTitleWander() {
        try {
            return driver.findElement(titleWander).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickSearchButton() {
        driver.findElement(searchBtn).click();
    }

    /*button in the search bar works correctly method*/
    public String getParisHotelsTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(parisHotelsTitle))
                .getText()
                .trim();
    }

    /*helper for getParisHotelsTitle*/
    public void openParisHotelsFromSearch() {
        clickSearchBar();
        sendKeysSearchBar();
        clickSearchButton();
    }

    public String getUrlAuthorisationPage() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isEntfernungFestlegenToggled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(entfernungFestlegenToggle));
        if (toggle.isEnabled()) {
            toggle.click();
            try {
                return new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.attributeContains(toggle, "class", "isChecked"));
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void clickProfileIconInHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(HomeLocator.PROFILE_ICON_IN_HEADER)).click();
    }

    public void clickLogoutLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(HomeLocator.LOGOUT_LINK)).click();
    }

    public boolean isCurrentPageContainLinkToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginIconElem = wait.until(ExpectedConditions.presenceOfElementLocated(loginIcon));
        String classes = loginIconElem.getAttribute("class");
        return classes != null && classes.contains("c24-login-opener");
    }

    public void сlickSectionPopularDestinationsForVacationRentals() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 3000)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(sectionPopularDestinationsForVacationRentals));
        element.click();
    }
}
