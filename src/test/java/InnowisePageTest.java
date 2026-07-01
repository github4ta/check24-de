import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class InnowisePageTest {

    WebDriver driver;
    Actions action;
    
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://innowise.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("UI-TC-001")
    public void innowiseCopirightTextTest() {
        action = new Actions(driver);
        WebElement copirightText = driver.findElement(By.xpath("//*[@class ='copyright-text']"));
        action.scrollToElement(copirightText).perform();

        Assertions.assertEquals("© 2007-2026 Innowise. All Rights Reserved.", copirightText.getText());
    }


}
