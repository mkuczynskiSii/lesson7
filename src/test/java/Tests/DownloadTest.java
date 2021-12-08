package Tests;

import Page.FormPage;
import Page.MainPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class DownloadTest extends TestBase {

    int numberOfFilesBeforeDownload=0;
    @Test
    @Order(1)
    public void downloadTest() throws InterruptedException {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();
        MainPage mainPage = new MainPage();

        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(mainPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement testFileToDownloadButton = driver.findElement(By.cssSelector(formPage.testFileToDownloadButtonCss));

        numberOfFilesBeforeDownload = Helpers.countFilesInDownloadDirectory();
        testFileToDownloadButton.click();
        sleep(1000);
        int numberOfFilesAfterDownload = Helpers.countFilesInDownloadDirectory();
        assertEquals(numberOfFilesBeforeDownload+1, numberOfFilesAfterDownload);
    }

    @Test
    @Order(2)
    public void isFileExist(){
        List<String> filesList = Helpers.listFilesInDownloadDirectory();
        assertTrue(filesList.contains("test-file-to-download.xlsx"));
    }

}
