import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void alertWithTextboxTest() {

        driver.get("https://demo.automationtesting.in/Alerts.html");

        WebElement textboxTab = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(),'Textbox')]")
                )
        );
        textboxTab.click();

        WebElement alertButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'prompt box')]")
                )
        );
        alertButton.click();

        driver.switchTo().alert().sendKeys("Bakari Akhvlediani");
        driver.switchTo().alert().accept();

        String resultText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("demo1"))
        ).getText();

        Assert.assertTrue(resultText.contains("Bakari Akhvlediani"));
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
