package Tests;

import Page.FormPage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class FormTest extends TestBase {

    @Test
    public void formTest() {


        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();
        MainPage mainPage = new MainPage();

        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(mainPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement firstName = driver.findElement(By.id(formPage.formFirstNameId));
        WebElement lastName = driver.findElement(By.id(formPage.formLastNameId));
        WebElement email = driver.findElement(By.id(formPage.formEmailId));
        WebElement sex = driver.findElement(By.id(formPage.formSexId));
        WebElement age = driver.findElement(By.id(formPage.formAgeId));
        WebElement yoe = driver.findElement(By.id(formPage.formRandomYOEId));
        WebElement profession = driver.findElement(By.id(formPage.formProfessionId));
        WebElement file = driver.findElement(By.id(formPage.formFileId));
        WebElement signInButton = driver.findElement(By.xpath(formPage.signInButtonXpath));
        Select continents = new Select(driver.findElement(By.id(formPage.formContinentsId)));
        Select seleniumCommands = new Select(driver.findElement(By.id(formPage.formSeleniumCommandsId)));


        String userFirstName = "Jan";
        String userLastName = "Kowalski";
        String usersEmail = "JanKowalski@firma.pl";
        String userAge = "21";
        String expectedMessage = "Form send with success";


        firstName.sendKeys(userFirstName);
        lastName.sendKeys(userLastName);
        email.sendKeys(usersEmail);
        sex.click();
        age.sendKeys(userAge);
        yoe.click();
        profession.click();
        continents.selectByIndex(formPage.formRandomContinentsIndex);
        seleniumCommands.selectByVisibleText("Switch Commands");
        seleniumCommands.selectByVisibleText("Wait Commands");
        file.sendKeys(formPage.filePath);
        signInButton.click();
        String validatorMessage = driver.findElement(By.id(formPage.validatorMessageId)).getText();
        assertEquals(expectedMessage, validatorMessage);
    }
}
