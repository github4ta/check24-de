package chelei;

import org.company.chelei.InnowiseMainPage;
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
        driver.get("https://innowise.com/");
    }

    @Test
    public void verifyCompanySloganText() {
        InnowiseMainPage page = new InnowiseMainPage(driver);
        String expectedSloganText = "Building scalable, high-quality software on time and on budget.";

        Assertions.assertEquals(expectedSloganText, page.getSloganText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
