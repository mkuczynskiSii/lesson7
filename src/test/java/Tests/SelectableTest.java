package Tests;

import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectableTest extends TestBase {

    MainPage mainPage = new MainPage();

    void selectItems(List<String> itemsList) {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).perform();
        for (String item : itemsList) {
            WebElement chosenItem = driver.findElement(By.xpath("//li[contains(text(), '" + item + "')]"));
            chosenItem.click();
        }
        action.keyUp(Keys.CONTROL).perform();
    }


    @Test
    public void resizableTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuInteractions = driver.findElement(By.linkText(mainPage.menuInteractionsLinkText));
        WebElement menuInteractionsSelectable = driver.findElement(By.id(mainPage.interactionsSelectableId));
        action.moveToElement(menuInteractions).moveToElement(menuInteractionsSelectable).click().build().perform();

        List<String> itemsList = new ArrayList<>();
        itemsList.add("Item 1");
        itemsList.add("Item 3");
        itemsList.add("Item 4");

        selectItems(itemsList);
        String message = driver.findElement(By.id("feedback")).getText();
        assertEquals("You've selected: #1 #3 #4.", message);
    }
}
