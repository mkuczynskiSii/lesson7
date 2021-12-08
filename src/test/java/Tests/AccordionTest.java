package Tests;

import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.sleep;

public class AccordionTest extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    public void accordionTest() throws InterruptedException {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsAccordion = driver.findElement(By.id(mainPage.widgetsAccordionId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsAccordion).click().build().perform();


        for (int i = 1; i <= 4; i++) {
            WebElement header = driver.findElement(By.id("ui-id-" + (i * 2 - 1)));
            if (i != 1) {
                header.click();
            }
            sleep(500);
            List<WebElement> boxContentList = driver.findElements(By.cssSelector("#ui-id-" + (i * 2) + ">*"));
            if (boxContentList.isEmpty()) {
                WebElement boxContent = driver.findElement(By.cssSelector("#ui-id-" + (i * 2)));
                System.out.println(boxContent.getText());
                continue;
            }
            for (WebElement content : boxContentList) {
                System.out.println(content.getText());
            }
        }
    }
}
