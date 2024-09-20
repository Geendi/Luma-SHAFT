package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;


public class WindowManager {
    private final SHAFT.GUI.WebDriver driver;
    private final By pageTitle = By.cssSelector("span.base");

    public WindowManager(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    public void hover(By element){
        driver.element().hover(element);
    }

    public String verifyPageTitle(){
        return driver.element().getText(pageTitle);
    }

    public String verifyText(By locator){
        return driver.element().getText(locator);
    }

    public void type(By locator, String text){
        driver.element().type(locator, text);
    }

    public void click(By locator){
        driver.element().click(locator);
    }
}
