package Tests;

import Page.MainPage;
import Page.WindowPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class WindowTest extends TestBase {

    MainPage mainPage = new MainPage();
    WindowPage windowPage = new WindowPage();

    @Test
    public void windowTest() {

        driver.get("https://seleniumui.moderntester.pl/");

        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicWindows = driver.findElement(By.id(mainPage.basicWindowId));
        action.moveToElement(menuBasic).moveToElement(menuBasicWindows).click().build().perform();
        String winHandleBefore = driver.getWindowHandle();

        WebElement newBrowserWindowButton = driver.findElement(By.id(windowPage.newBrowserWindowButtonId));
        newBrowserWindowButton.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        TableTests tabletest = new TableTests();
        List<WebElement> tableResults = driver.findElements(By.cssSelector(windowPage.rowCssSelector));
        tabletest.display4000HighMountainsInSwitzerland(tableResults);
        driver.close();
        driver.switchTo().window(winHandleBefore);

        WebElement newMessageWindowButton = driver.findElement(By.id("newMessageWindow"));
        newMessageWindowButton.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        String messageWindowText = driver.findElement(By.cssSelector("body")).getText();
        System.out.println(messageWindowText);
        driver.close();
        driver.switchTo().window(winHandleBefore);

        WebElement newBrowserTabButton = driver.findElement(By.id("newBrowserTab"));
        newBrowserTabButton.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        tableResults = driver.findElements(By.cssSelector(windowPage.rowCssSelector));
        tabletest.display4000HighMountainsInSwitzerland(tableResults);
        driver.close();
    }
}
