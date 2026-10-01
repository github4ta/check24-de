package novysh;

import org.company.novysh.ConfigReader;
import org.company.novysh.ElementUtils;
import org.company.novysh.InnowiseMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InnowiseTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());
    }

    @Test
    public void verifyCompanySloganText(){
        InnowiseMainPage innowiseMainPage = new InnowiseMainPage(driver);
        String actualSloganText = ElementUtils.getText(innowiseMainPage.getSloganText());
        String expectedSloganText = "Building scalable, high-quality software on time and on budget.";

        Assertions.assertEquals(expectedSloganText,actualSloganText);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
