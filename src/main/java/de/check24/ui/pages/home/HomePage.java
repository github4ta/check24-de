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

/**
 * Page Object for Check24 HomePage
 */
public class HomePage {
    private final WebDriver driver;

    private final By logo = By.cssSelector("a[data-testid='header-logo']");
    private final By linkFerienwohnung = By.xpath("//div[text()='Ferienwohnung buchen']");
    private final By searchInput = By.cssSelector("input[data-testid='search-input']");
    private final By searchButton = By.cssSelector("button[data-testid='search-button']");
    private final By searchInHeader = By.xpath("//*[@id='c24-search-header']");
    private final By copyrightText = By.xpath("//*[contains(text(),'© 2026 CHECK24 Vergleichsportal GmbH München')]");
    private final By copyrightFooter = By.cssSelector("footer [class*='copyright'], footer p, .footer-copyright");
    private final By anyCopyright2026 = By.xpath("//*[contains(text(),'2026') and contains(text(),'CHECK24')]");
    private final By cookieAcceptButton = By.xpath("//a[text()='geht klar']");
    private final By enterEmail = By.xpath("//*[@id=\"cl_login\"]");
    private final By forgotPassword = By.xpath("//*[@id=\"c24-content\"]/div/div/div/div/unified-login//div/div/div[2]/form/div[2]/div[1]/div/a/div/div[1]/font/font");
    private final By sectionTurkey = By.xpath("//*[@id=\"c24trendingLocations\"]/div/a[2]/div/div[2]/div[1]");
    private final By labelWarschau = By.xpath("//span[text()='warschau']");
    private final By personalAccountButton = By.xpath("//*[@id=\"c24-header-top\"]/div/div[2]/div[5]/a");
    private final By agbLink = By.xpath("//a[@title='AGB']");
    private final By socialIcon = By.xpath("//a[@class='c24-footer-icon']");
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
    private final By impressumLink = By.xpath("//a[@title='Impressum']");
    private final By sportWander = By.xpath("//a[@href='https://individualreisen.check24.de/wandern?tid=widget']");
    private final By titleWander =  By.xpath("//h1");
    private final By searchBtn = By.xpath("//div[@class='c24-search-button']");
    private final By titleParisHotels = By.xpath("//div[@class='travel-widget__form-title travel-widget__form-title--desktop new']");
    private final By entfernungFestlegenToggle = By.xpath("//button[@type='button' and contains(@class, 'slideToggle')]");
    private final By sectionPopularDestinationsForVacationRentals = By.xpath("//*[@id='c24-container-18']/div[6]/div[2]/a[4]/div[1]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to Check24 homepage
     */
    public void navigateToHomePage() {
        driver.get("https://www.check24.de/");
    }

    /**
     * Check if logo is displayed
     */
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    /**
     * Check if search input is present
     */
    public boolean isSearchInputPresent() {
        try {
            return driver.findElement(searchInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if search button is present
     */
    public boolean isSearchButtonPresent() {
        try {
            return driver.findElement(searchButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if the specific copyright text is present
     */
    public boolean isCopyrightTextPresent() {
        try {
            return driver.findElement(copyrightText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if any copyright text with 2026 and CHECK24 is present
     */
    public boolean isCopyright2026Present() {
        try {
            return driver.findElement(anyCopyright2026).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the actual copyright text from footer
     */
    public String getCopyrightText() {
        try {
            return driver.findElement(copyrightFooter).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verify that page is loaded and basic elements are present
     */
    public boolean isPageLoaded() {
        return driver.getTitle().contains("CHECK24") ||
               driver.getCurrentUrl().contains("check24.de");
    }

    /**
     * Search for a term
     */
    public void search(String searchTerm) {
        try {
            WebElement input = driver.findElement(searchInHeader);
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

    public void clickSectionTurkey() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        js.executeScript("window.scrollBy(0, 1000)");

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(sectionTurkey));
        element.click();
    }

    public void clickLogoCheckInHeader() {
        driver.findElement(LOGIN_CHECK_IN_HEADER).click();
    }

    public void clickLinkFerienwohnung() {
        driver.findElement(linkFerienwohnung).click();
    }

    public void clickToSearchFieldInHeader() {
        driver.findElement(searchInHeader).click();
    }

    public void fillInputInSearchHeader(String value) {
        driver.findElement(searchInHeader).sendKeys(value);
    }

    public void submitSearchByEnter() {
        driver.findElement(searchInHeader).sendKeys(Keys.ENTER);
    }

    public String getTextAfterSearchWarsaw() {
       return driver.findElement(labelWarschau).getText().replace("\"", "").trim();
    }

    public void clickFacebookButton() {
        driver.findElement(FACEBOOK_BUTTON).click();
    }

    public String getFacebookPageUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void clickLoginIcon() {
        driver.findElement(LOGIN_IKON).click();
    }

    public void enterEmail() {
        driver.findElement(enterEmail).click();
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }

        public String getAgbLinkHref() {
            return driver.findElement(AGB_LINK).getAttribute("href");
        }

    public String getSearchHotelInputPlaceholder() {
        try {
            WebElement input = driver.findElement(SEARCH_HOTEL_INPUT);
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            return "";
        }
    }

    public void clickSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BAR)).click();
    }

    public void sendKeysSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BAR)).sendKeys("Paris", Keys.ENTER);
    }

    public String getParisHotelCurrentUrl() { return driver.getCurrentUrl();}
    public boolean isPersonalAccountButtonDisplayed() {
        return driver.findElement(personalAccountButton).isDisplayed();
    }

    public boolean isAGBLinkClickable() {
        try {
            driver.findElement(agbLink).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getSocialIconCount() {
        try {
            return driver.findElements(socialIcon).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickToSearchFieldInHeaderUsingActions() {
        WebElement element = driver.findElement(searchInHeader);

        Actions actions = new Actions(driver);
        actions
                .moveToElement(element)
                .pause(Duration.ofMillis(400))
                .click()
                .perform();
    }

    public void fillInputInSearchHeaderUsingActions(String value) {
        WebElement input = driver.findElement(searchInHeader);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(searchParisHotelsButton));

        new Actions(driver)
                .moveToElement(button)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }

    public void clickOnPopupWindowCross() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cross = wait.until(ExpectedConditions.elementToBeClickable(popupSplashScreenSpringDealContainer));

        new Actions(driver)
                .moveToElement(cross)
                .pause(Duration.ofMillis(400))
                .click()
                .perform();
    }

    public void clickOnSortingField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By locator = By.xpath("//div[contains(@class, 'sorting')]//span/div");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        new Actions(driver).scrollToElement(element).perform();
        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }

    public void clickOnSortingByPopularityInDescendingOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(sortingByPopularityInDescendingOrder)).click();
    }

    /*Select sorting by price method*/
    public void selectSortingByPriceAscending() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(sortingByPriceAscending));
        option.click();
    }

    public boolean checkIfSortingByPopularityInDescendingOrderIsWorking() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> hotels = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@data-test-id-qa=\"static-results-list-result\"]"))
        );
        if(hotels.size()>2){
            String priceText1 = hotels.get(0).findElement(By.xpath(".//*[@data-test-id-qa=\"hotel-rating\"]")).getText();
            String priceText2 = hotels.get(1).findElement(By.xpath(".//*[@data-test-id-qa=\"hotel-rating\"]")).getText();
            double rating1 = Double.parseDouble(priceText1.replaceAll("[^0-9.]", ""));
            double rating2 = Double.parseDouble(priceText2.replaceAll("[^0-9.]", ""));
            return rating1 >= rating2;
        }
        return false;
    }

    /*Check sorting by price method*/
    public  boolean checkIsSortingByPriceAscending() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public String getTitleParisHotels() {
        try {
            return driver.findElement(titleParisHotels).getText();
        } catch (Exception e) {
            return "";
        }
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
        WebElement loginIconElem = wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN_IKON));
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
