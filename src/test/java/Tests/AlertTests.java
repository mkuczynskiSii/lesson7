package Tests;

import Page.AlertPage;
import Page.MainPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class AlertTests extends TestBase {

    MainPage mainPage = new MainPage();
    AlertPage alertPage = new AlertPage();

    @Test
    @Order(1)
    public void simpleAlert() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicAlerts = driver.findElement(By.id(mainPage.basicAlertsId));
        action.moveToElement(menuBasic).moveToElement(menuBasicAlerts).click().build().perform();
        WebElement simpleAlertButton = driver.findElement(By.id(alertPage.alertsSimpleAlertButtonId));

        simpleAlertButton.click();
        driver.switchTo().alert().accept();
        String alertLabel = driver.findElement(By.id("simple-alert-label")).getText();
        assertEquals(alertLabel, "OK button pressed");
        driver.close();
    }
    @Test
    @Order(2)
    public void promptAlert() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicAlerts = driver.findElement(By.id(mainPage.basicAlertsId));
        action.moveToElement(menuBasic).moveToElement(menuBasicAlerts).click().build().perform();
        WebElement promptAlertButton = driver.findElement(By.id(alertPage.alertsPromptAlertButtonId));

        promptAlertButton.click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        String promptAlertLabel = driver.findElement(By.id("prompt-label")).getText();
        assertEquals(promptAlertLabel, "Hello Lord Vader! How are you today?");
        driver.close();
    }

    @Test
    @Order(3)
    public void confirmAlert() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicAlerts = driver.findElement(By.id(mainPage.basicAlertsId));
        action.moveToElement(menuBasic).moveToElement(menuBasicAlerts).click().build().perform();

        WebElement confirmAlertButton = driver.findElement(By.id(alertPage.alertsConfirmAlertButtonId));
        confirmAlertButton.click();
        driver.switchTo().alert().accept();
        String confirmAlertLabel = driver.findElement(By.id("confirm-label")).getText();
        assertEquals(confirmAlertLabel, "You pressed OK!");
        confirmAlertButton.click();
        driver.switchTo().alert().dismiss();
        confirmAlertLabel = driver.findElement(By.id("confirm-label")).getText();
        assertEquals(confirmAlertLabel, "You pressed Cancel!");
        driver.close();
    }

    @Test
    @Order(4)
    public void delayedAlert() {

        driver.get("https://seleniumui.moderntester.pl/");
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));

        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicAlerts = driver.findElement(By.id(mainPage.basicAlertsId));
        action.moveToElement(menuBasic).moveToElement(menuBasicAlerts).click().build().perform();

        WebElement delayedAlertButton = driver.findElement(By.id(alertPage.alertsDelayedAlertButtonId));
        delayedAlertButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        String delayedAlertLabel = driver.findElement(By.id("delayed-alert-label")).getText();
        assertEquals(delayedAlertLabel, "OK button pressed");
        driver.close();
    }
}

