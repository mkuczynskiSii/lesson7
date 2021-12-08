package Tests;

import Page.MainPage;
import Page.SliderPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SliderTest extends TestBase {


    MainPage mainPage = new MainPage();
    SliderPage sliderPage = new SliderPage();

    public void moveSliderTo(int expectedSliderValue) {
        String sliderValue = driver.findElement(By.id(sliderPage.sliderId)).getText();
        WebElement slider = driver.findElement(By.id(sliderPage.sliderId));

        while (expectedSliderValue != Integer.parseInt(sliderValue)) {
            if (expectedSliderValue > Integer.parseInt(sliderValue)) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            } else if (expectedSliderValue < Integer.parseInt(sliderValue)) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
            sliderValue = driver.findElement(By.id(sliderPage.sliderId)).getText();
        }

    }

    @Test
    public void sliderTest() {


        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuWidgets = driver.findElement(By.linkText(mainPage.menuWidgetsLinkText));
        WebElement menuWidgetsSlider = driver.findElement(By.id(mainPage.widgetsSliderId));
        action.moveToElement(menuWidgets).moveToElement(menuWidgetsSlider).click().build().perform();

        WebElement slider = driver.findElement(By.id(sliderPage.sliderId));
        moveSliderTo(50);
        assertEquals(50, Integer.parseInt(slider.getText()));
        moveSliderTo(80);
        assertEquals(80, Integer.parseInt(slider.getText()));
        moveSliderTo(80);
        assertEquals(80, Integer.parseInt(slider.getText()));
        moveSliderTo(20);
        assertEquals(20, Integer.parseInt(slider.getText()));
        moveSliderTo(0);
        assertEquals(0, Integer.parseInt(slider.getText()));
    }
}
