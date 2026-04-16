package de.check24.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for Check24 HomePage
 */
public class HomePage {
    private final WebDriver driver;

    // Locators for main elements
    private final By logo = By.cssSelector("a[data-testid='header-logo']");
    private final By searchInput = By.cssSelector("input[data-testid='search-input']");
    private final By searchButton = By.cssSelector("button[data-testid='search-button']");

    // Footer copyright locator
    private final By copyrightText = By.xpath("//*[contains(text(),'© 2026 CHECK24 Vergleichsportal GmbH München')]");

    // Alternative copyright locators (in case the text changes)
    private final By copyrightFooter = By.cssSelector("footer [class*='copyright'], footer p, .footer-copyright");
    private final By anyCopyright2026 = By.xpath("//*[contains(text(),'2026') and contains(text(),'CHECK24')]");
    private final By facebookButton = By.xpath("//*[@id=\"c24-footer\"]/div[2]/div[2]/a[1]");
    private final By cookieAcceptButton = By.xpath("//*[@id=\"c24-html\"]/body/div[2]/div[1]/div[3]/a[2]");
    private final By BreakfastFilter = By.xpath("//*[@id=\"travel-search-form\"]/div[2]/div[2]/div/fieldset[3]/div[1]/div[7]/label");

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
        try {
            return driver.findElement(logo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
            driver.findElement(searchInput).clear();
            driver.findElement(searchInput).sendKeys(searchTerm);
            driver.findElement(searchButton).click();
        } catch (Exception e) {
            throw new RuntimeException("Search functionality failed: " + e.getMessage());
        }
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

    public void selectBreakfastFilter() {
        driver.findElement(BreakfastFilter).isSelected();
    }

    public List<WebElement> getHotelCards() {
        return driver.findElements(By.cssSelector(".hotel-card"));
    }

    public List<WebElement> getBreakfastBadges() {
        return driver.findElements(By.xpath("//span[contains(text(), 'Frühstück inbegriffen')]"));
    }
}
