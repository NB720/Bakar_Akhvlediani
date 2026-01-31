package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FormPage {

    WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("userEmail");
    By genderMale = By.xpath("//label[contains(text(),'Male')]");
    By phone = By.id("userNumber");
    By submit = By.id("submit");
    By modalBody = By.className("modal-body");

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void fillForm() {
        driver.findElement(firstName).sendKeys("Bakari");
        driver.findElement(lastName).sendKeys("Akhvlediani");
        driver.findElement(email).sendKeys("bakari@test.com");
        driver.findElement(genderMale).click();
        driver.findElement(phone).sendKeys("1234567890");
    }

    public void submitForm() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(submit));
        driver.findElement(submit).click();
    }

    public String getModalText() {
        return driver.findElement(modalBody).getText();
    }
}
