package Tests;

import Page.MainPage;
import Page.TooltipPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TooltipTest extends TestBase {

    MainPage mainPage = new MainPage();
    TooltipPage tooltipPage = new TooltipPage();


    @Test
    public void sliderTest() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsTooltip = driver.findElement(By.id(mainPage.widgetsTooltipId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsTooltip).click().build().perform();

        WebElement ageInput = driver.findElement(By.id(tooltipPage.ageInputId));
        String tooltipText = ageInput.getAttribute("title");
        System.out.println(tooltipText);
    }
}
