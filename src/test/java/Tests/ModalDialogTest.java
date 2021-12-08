package Tests;

import Page.MainPage;
import Page.ModalDialogPage;
import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModalDialogTest extends TestBase {
    MainPage mainPage = new MainPage();
    ModalDialogPage modalDialogPage = new ModalDialogPage();

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                {"Jan Kowalski", "kowalski@wp.pl", "haslo"},
                {"Adam Nowak", "nowak@wp.pl", "haslo"}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void modalDialogTest(String userName, String userEmail, String userPass) {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsModalDialog = driver.findElement(By.id(mainPage.widgetsModalDialogId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsModalDialog).click().build().perform();

        WebElement newUserButton = driver.findElement(By.id(modalDialogPage.newUserButtonId));
        WebElement name = driver.findElement(By.id(modalDialogPage.formNameId));
        WebElement email = driver.findElement(By.id(modalDialogPage.formEmailId));
        WebElement password = driver.findElement(By.id(modalDialogPage.formPassId));
        WebElement createAccountButton = driver.findElement(By.xpath(modalDialogPage.createAccountButtonXpath));

        newUserButton.click();
        name.clear();
        email.clear();
        password.clear();
        name.sendKeys(userName);
        email.sendKeys(userEmail);
        password.sendKeys(userPass);
        createAccountButton.click();

        List<WebElement> newUser = driver.findElements(By.cssSelector(modalDialogPage.newAddedRowCssSelector));
        assertEquals(userName, newUser.get(0).getText());
        assertEquals(userEmail, newUser.get(1).getText());
        assertEquals(userPass, newUser.get(2).getText());
    }
}
