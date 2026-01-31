package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

@Epic("Homework 3")
@Feature("Automation Practice Form")
public class FormTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @Story("Submit form and verify popup data")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fill the form with Bakari Akhvlediani data and verify submission popup")
    void submitFormTest() {

        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Bakari");
        driver.findElement(By.id("lastName")).sendKeys("Akhvlediani");
        driver.findElement(By.id("userEmail")).sendKeys("bakari@test.com");
        driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", submitButton);

        submitButton.click();

        WebElement modalTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg"))
        );

        Assert.assertTrue(modalTitle.isDisplayed());

        String modalText = driver.findElement(By.className("modal-body")).getText();

        Assert.assertTrue(modalText.contains("Bakari"));
        Assert.assertTrue(modalText.contains("Akhvlediani"));
        Assert.assertTrue(modalText.contains("bakari@test.com"));
        Assert.assertTrue(modalText.contains("Male"));
        Assert.assertTrue(modalText.contains("1234567890"));
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
