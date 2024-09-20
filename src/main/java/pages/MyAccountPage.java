package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class MyAccountPage {
    private SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public MyAccountPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By accountDropdown = By.cssSelector("div[class='panel header'] button[type='button']");
    private final By myAcc = By.linkText("My Account");

    public void openMyAccount(){
        driver.element().click(accountDropdown).click(myAcc);
    }
}
