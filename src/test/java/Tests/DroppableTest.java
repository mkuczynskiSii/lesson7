package Tests;

import Page.DroppablePage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroppableTest extends TestBase {

    MainPage mainPage = new MainPage();
    DroppablePage droppablePage = new DroppablePage();


    @Test
    public void droppableTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuInteractions = driver.findElement(By.linkText(mainPage.menuInteractionsLinkText));
        WebElement menuInteractionsDroppable = driver.findElement(By.id(mainPage.interactionsDroppableId));
        action.moveToElement(menuInteractions).moveToElement(menuInteractionsDroppable).click().build().perform();

        WebElement draggableElement = driver.findElement(By.id(droppablePage.draggableElementId));
        WebElement droppableArea = driver.findElement(By.id(droppablePage.draoppableAreaId));

        action.dragAndDrop(draggableElement,droppableArea).perform();
        String textMessage = droppableArea.getText();
        assertEquals("Dropped!",textMessage);
    }
}
