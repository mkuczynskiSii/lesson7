package Tests;

import Page.MainPage;
import Page.ResizablePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

public class ResizableTest extends TestBase {

    MainPage mainPage = new MainPage();
    ResizablePage resizablePage = new ResizablePage();


    @Test
    public void resizableTest() throws InterruptedException {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuInteractions = driver.findElement(By.linkText(mainPage.menuInteractionsLinkText));
        WebElement menuInteractionsResizable = driver.findElement(By.id(mainPage.interactionsResizableId));
        action.moveToElement(menuInteractions).moveToElement(menuInteractionsResizable).click().build().perform();
        sleep(100);
        WebElement windowCorner = driver.findElement(By.cssSelector(resizablePage.windowCornerCssSelector));
        Helpers.moveElementBy(driver, windowCorner, 28, 0);
        sleep(100);
        Helpers.moveElementBy(driver, windowCorner, 0, 28);
        sleep(100);
        Helpers.moveElementBy(driver, windowCorner, 28, 28);

    }
}