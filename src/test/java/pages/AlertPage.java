package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage {

    WebDriver driver;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
    }

    By textboxTab = By.xpath("//a[contains(text(),'Textbox')]");
    By alertButton = By.xpath("//button[contains(text(),'prompt box')]");
    By resultText = By.id("demo1");

    public void open() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
    }

    public void triggerPromptAlert(String text) {
        driver.findElement(textboxTab).click();
        driver.findElement(alertButton).click();
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
    }

    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}
