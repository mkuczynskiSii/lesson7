package Tests;

import Page.MainPage;
import Page.SelectMenuPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.ThreadLocalRandom;

public class SelectMenuTest extends TestBase {
    MainPage mainPage = new MainPage();
    SelectMenuPage selectMenuPage = new SelectMenuPage();

    @Test
    public void selectMenuTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsSelectMenu = driver.findElement(By.id(mainPage.widgetsSelectMenuId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsSelectMenu).click().build().perform();
        ((JavascriptExecutor) driver).executeScript("jQuery('select').css('display','block')");

        Select speedSelect = new Select(driver.findElement(By.id(selectMenuPage.speedSelectId)));
        Select filesSelect = new Select(driver.findElement(By.id(selectMenuPage.filesSelectId)));
        Select numberSelect = new Select(driver.findElement(By.id(selectMenuPage.numberSelectId)));
        Select salutationSelect = new Select(driver.findElement(By.id(selectMenuPage.salutationSelectId)));

        int randomSpeedIndex = ThreadLocalRandom.current().nextInt(0, speedSelect.getOptions().size());
        speedSelect.selectByIndex(randomSpeedIndex);

        filesSelect.selectByVisibleText("Some unknown file");
        numberSelect.selectByIndex(0);

        int randomSalutationIndex = ThreadLocalRandom.current().nextInt(0, salutationSelect.getOptions().size());
        salutationSelect.selectByIndex(randomSalutationIndex);
    }
}
