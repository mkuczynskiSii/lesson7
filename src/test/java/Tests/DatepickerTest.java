package Tests;

import Page.DatepickerPage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatepickerTest extends TestBase {

    MainPage mainPage = new MainPage();
    DatepickerPage datepickerPage = new DatepickerPage();

    Calendar cal = Calendar.getInstance();
    int presentYear = cal.get(Calendar.YEAR);
    int presentMonth = cal.get(Calendar.MONTH);
    int presentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);


    @Test
    public void datepickerTest() {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsDatepicker = driver.findElement(By.id(mainPage.widgetsDatepickerId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsDatepicker).click().build().perform();

        setDate(presentDayOfMonth, presentMonth, presentYear);
        setDate(1, presentMonth + 1, presentYear);
        setDate(31, 0, presentYear + 1);
        setDate(31, 0, presentYear + 1);
        setRandomDayFromPrevMonth();
        setRandomDayFromPrevYear();
    }

    void setDate(int day, int month, int year) {
        if (month > 11) month = 0;
        waitUntilCalendarYearHeaderVisible();
        setCorrectYear(year);
        setCorrectMonth(month);
        WebElement calendarDay = driver.findElement(By.xpath("//td[@data-month=" + month + "]/a[text()= '" + day + "']"));
        calendarDay.click();

        String assertDay = (day < 10 && day > 0) ? "0" + day : String.valueOf(day);
        month += 1;
        String assertMonth = (month < 10 && month > 0) ? "0" + month : String.valueOf(month);

        assertEquals(assertMonth + '/' + assertDay + '/' + year, driver.findElement(By.id(datepickerPage.inputId)).getAttribute("value"));
    }


    public void setCorrectYear(int year) {
        int yearOnCalendar = Integer.parseInt(driver.findElement(By.className(datepickerPage.callenderHeaderYearClass)).getText());
        if (year > yearOnCalendar) {
            while (year != yearOnCalendar) {
                WebElement rightArrow = driver.findElement(By.cssSelector(datepickerPage.nextArrowCssSelector));
                rightArrow.click();
                yearOnCalendar = Integer.parseInt(driver.findElement(By.className(datepickerPage.callenderHeaderYearClass)).getText());
            }
        } else if (year < yearOnCalendar) {
            while (year != yearOnCalendar) {
                WebElement leftArrow = driver.findElement(By.cssSelector(datepickerPage.previousArrowCssSelector));
                leftArrow.click();
                yearOnCalendar = Integer.parseInt(driver.findElement(By.className(datepickerPage.callenderHeaderYearClass)).getText());
            }
        }
    }

    public void setCorrectMonth(int month) {
        int monthOnCalendar = Integer.parseInt(driver.findElement(By.cssSelector(datepickerPage.callenderRowCssSelector)).getAttribute("data-month"));
        if (month > monthOnCalendar) {
            while (month != monthOnCalendar) {
                WebElement rightArrow = driver.findElement(By.cssSelector(datepickerPage.nextArrowCssSelector));
                rightArrow.click();
                monthOnCalendar = Integer.parseInt(driver.findElement(By.cssSelector(datepickerPage.callenderRowCssSelector)).getAttribute("data-month"));
            }
        } else if (month < monthOnCalendar) {
            while (month != monthOnCalendar) {
                WebElement leftArrow = driver.findElement(By.cssSelector(datepickerPage.previousArrowCssSelector));
                leftArrow.click();
                monthOnCalendar = Integer.parseInt(driver.findElement(By.cssSelector(datepickerPage.callenderRowCssSelector)).getAttribute("data-month"));
            }
        }
    }

    public void setRandomDayFromPrevMonth() {
        waitUntilCalendarYearHeaderVisible();
        setCorrectYear(presentYear);
        setCorrectMonth(presentMonth - 1);
        List<WebElement> daysList = driver.findElements(By.cssSelector("td[data-month='" + (presentMonth - 1) + "']"));
        int randomDay = ThreadLocalRandom.current().nextInt(0, daysList.size());
        WebElement calendarDay = driver.findElement(By.xpath("//td[@data-month=" + (presentMonth - 1) + "]/a[text()= '" + randomDay + "']"));
        calendarDay.click();
        System.out.println("---RandomDay--" + randomDay);
    }

    public void setRandomDayFromPrevYear() {
        waitUntilCalendarYearHeaderVisible();
        setCorrectYear(presentYear - 1);
        int randomMonth = ThreadLocalRandom.current().nextInt(0, 12);
        setCorrectMonth(randomMonth);
        List<WebElement> daysList = driver.findElements(By.cssSelector("td[data-month='" + randomMonth + "']"));
        int randomDay = ThreadLocalRandom.current().nextInt(0, daysList.size());
        WebElement callenderDay = driver.findElement(By.xpath("//td[@data-month=" + randomMonth + "]/a[text()= '" + randomDay + "']"));
        callenderDay.click();
    }

    public void waitUntilCalendarYearHeaderVisible() {
        WebElement input = driver.findElement(By.id(datepickerPage.inputId));
        input.click();

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(datepickerPage.callenderHeaderYearClass)));
    }
}
