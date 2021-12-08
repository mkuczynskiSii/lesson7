package Tests;

import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TableTests extends TestBase {

    MainPage mainPage = new MainPage();

    public void display4000HighMountainsInSwitzerland(List<WebElement> tableResults) {
        int i = 1;
        for (WebElement result : tableResults) {
            if (i <= tableResults.size() - 2) {
                String rank = result.findElement(By.xpath("//tbody/tr[" + i + "]/th")).getText();
                String peak = result.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText();
                String mountainRange = result.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText();
                String state = result.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]")).getText();
                String height = result.findElement(By.xpath("//tbody/tr[" + i + "]/td[4]")).getText();

                if (Integer.parseInt(height) > 4000 && state.contains("Switzerland")) {
                    System.out.println(rank + " " + peak + " " + mountainRange);
                }
                i++;
            }
        }
    }

    @Test
    public void tableTest() {

        driver.get("https://seleniumui.moderntester.pl/");

        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicTable = driver.findElement(By.id(mainPage.basicTableId));
        action.moveToElement(menuBasic).moveToElement(menuBasicTable).click().build().perform();

        List<WebElement> tableResults = driver.findElements(By.cssSelector("table.table tr"));
        display4000HighMountainsInSwitzerland(tableResults);
    }

}
