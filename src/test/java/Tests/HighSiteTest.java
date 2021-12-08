package Tests;

import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Function;

public class HighSiteTest extends TestBase {
    MainPage mainPage = new MainPage();

    WebElement submitButton = null;

    @Test
    public void selectMenuTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuOthers = driver.findElement(By.linkText(mainPage.menuOthersLinkText));
        WebElement menuOthersHighSite = driver.findElement(By.id(mainPage.othersHighSiteId));
        action.moveToElement(menuOthers).moveToElement(menuOthersHighSite).click().build().perform();

        scrollDownToScrollButton();
        submitButton.click();
        takeScreenshot();
    }


    public Function scrollDownToScrollButton() {
        try {
            submitButton = driver.findElement(By.id("scroll-button"));
        } catch (NoSuchElementException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,100)");
            return scrollDownToScrollButton();
        }
        return null;
    }
}
