package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class ChangePasswordPage {
    private final SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public ChangePasswordPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By changePassButton = By.partialLinkText("Change Password");
    private final By currentPassField = By.id("current-password");
    private final By confirmPassField = By.id("password-confirmation");
    private final By saveButton = By.xpath("//span[normalize-space()='Save']");
    private final By currentPassErrors = By.id("current-password-error");
    private final By changePassCheckBox = By.id("change-password");

    public void goToChangePass(){
        windowManager.click(changePassButton);
    }

    public void setCurrentPass(String uPass){
        windowManager.type(currentPassField, uPass);
    }

    public void setConfirmPass(String cPass){
        windowManager.type(confirmPassField, cPass);
    }

    public void clickSave(){
        windowManager.click(saveButton);
    }

    public String verifyEmptyCurrentPass(){
        return windowManager.verifyText(currentPassErrors);
    }

    public void selectChangePassBox(){
        windowManager.click(changePassCheckBox);
    }



}
