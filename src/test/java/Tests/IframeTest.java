package Tests;

import Page.FormPage;
import Page.IframPage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class IframeTest extends TestBase {

    MainPage mainPage = new MainPage();
    FormPage formPage = new FormPage();
    IframPage iframePage = new IframPage();


    @Test
    public void iframeTest() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicIframes = driver.findElement(By.id(mainPage.basicIframesId));
        action.moveToElement(menuBasic).moveToElement(menuBasicIframes).click().build().perform();

        WebElement iframe2 = driver.findElement(By.name(iframePage.iframe2Name));
        WebElement iframe1 = driver.findElement(By.name(iframePage.iframe1Name));

        driver.switchTo().frame(iframe1);

        WebElement firstName = driver.findElement(By.id(formPage.formFirstNameId));
        WebElement surname = driver.findElement(By.id(iframePage.iframeSurnameId));

        firstName.sendKeys("Mateusz");
        surname.sendKeys("Kuczynski");

        driver.switchTo().parentFrame();
        driver.switchTo().frame(iframe2);

        WebElement login = driver.findElement(By.id(iframePage.iframesLoginId));
        WebElement password = driver.findElement(By.id(iframePage.iframesPasswordId));
        WebElement iframeYoe = driver.findElement(By.id(iframePage.iframesSelectYOEId));
        Select iframeContinents = new Select(driver.findElement(By.id(iframePage.iframesSelectContinentsId)));

        login.sendKeys("Login");
        password.sendKeys("Haslo");
        iframeContinents.selectByIndex(iframePage.formContinentsIndex);
        iframeYoe.click();

        driver.switchTo().parentFrame();
        WebElement menuBasic2 = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        menuBasic2.click();
        driver.close();
    }
}
