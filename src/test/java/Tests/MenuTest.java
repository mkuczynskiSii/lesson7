package Tests;

import Page.MainPage;
import Page.MenuPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class MenuTest extends TestBase {

    MainPage mainPage = new MainPage();
    MenuPage menuPage = new MenuPage();

    @Test
    public void menuTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsMenu = driver.findElement(By.id(mainPage.widgetsMenuId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsMenu).click().build().perform();


        WebElement music = driver.findElement(By.id(menuPage.musicId));
        WebElement jazz = driver.findElement(By.id(menuPage.jazzId));
        WebElement modern = driver.findElement(By.id(menuPage.modernId));

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(menuPage.musicId)));
        music.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(menuPage.jazzId)));
        jazz.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(menuPage.modernId)));
        modern.click();
    }
}
