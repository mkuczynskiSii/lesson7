package Tests;

import Page.AutocompletePage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutocompleteTest extends TestBase {
    MainPage mainPage = new MainPage();
    AutocompletePage autocompletePage = new AutocompletePage();

    @Test
    public void autocompleteTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsAutocomplete = driver.findElement(By.id(mainPage.widgetsAutocompleteId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsAutocomplete).click().build().perform();

        WebElement input = driver.findElement(By.id(autocompletePage.inputId));
        input.sendKeys("a");
        List<WebElement> resultsList = driver.findElements(By.cssSelector(autocompletePage.resultCssSelector));
        for (WebElement result : resultsList) {
            System.out.println(result.getText());
        }
        int randomNumber = ThreadLocalRandom.current().nextInt(0, resultsList.size());

        String chosenValue = resultsList.get(randomNumber).getText();
        resultsList.get(randomNumber).click();
        assertEquals(chosenValue, driver.findElement(By.id(autocompletePage.inputId)).getAttribute("value"));
    }
}
