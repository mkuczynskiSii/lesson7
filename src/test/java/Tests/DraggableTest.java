package Tests;

import Page.DraggablePage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DraggableTest extends TestBase {

    MainPage mainPage = new MainPage();
    DraggablePage draggablePage = new DraggablePage();


    @Test
    public void draggableTest(){
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuInteractions = driver.findElement(By.linkText(mainPage.menuInteractionsLinkText));
        WebElement menuInteractionsDraggable = driver.findElement(By.id(mainPage.interactionsDraggableId));
        action.moveToElement(menuInteractions).moveToElement(menuInteractionsDraggable).click().build().perform();

        WebElement square = driver.findElement(By.id(draggablePage.squareItemId));

        Helpers.moveElementBy(driver, square, 1238, 0);
        Helpers.moveElementBy(driver, square, 0, 432);
        Helpers.moveElementBy(driver, square, -619, -216);
        Helpers.moveElementBy(driver, square, -619, 216);
    }
}
