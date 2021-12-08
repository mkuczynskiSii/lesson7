package Tests;

import Page.MainPage;
import Page.ProgressBarPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ProgressBarTest extends TestBase {
    MainPage mainPage = new MainPage();
    ProgressBarPage progressBarPage = new ProgressBarPage();

    @Test
    public void progressBarTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsProgressBar = driver.findElement(By.id(mainPage.widgetsProgressBarId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsProgressBar).click().build().perform();

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));
        WebElement progressBar = driver.findElement(By.cssSelector(progressBarPage.progressBarCssSelector));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "Complete!"));
    }
}
