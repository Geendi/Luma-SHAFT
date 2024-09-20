package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class LoginPage {
    private SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public LoginPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By loginPage = By.partialLinkText("Sign In");
    private final By emailField = By.id("email");
    private final By passwordField = By.xpath("//fieldset[@class='fieldset login']//input[@id='pass']");
    private final By signInButton = By.cssSelector("button.action.login.primary");
    private final By emailErrorMsg = By.id("email-error");
    private final By passErrorMsg = By.id("pass-error");

    public void openLoginPage(){
        windowManager.click(loginPage);
    }

    public void setEmail(String mail){
        windowManager.type(emailField, mail);
    }

    public void setPassword(String pass){
        windowManager.type(passwordField, pass);
    }

    public void clickSignIn(){
        windowManager.click(signInButton);
    }

    public String verifyEmailErrors(){
        return windowManager.verifyText(emailErrorMsg);
    }

    public String verifyPassErrors(){
        return windowManager.verifyText(passErrorMsg);
    }
}
