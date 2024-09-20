package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class RegistrationPage {
    private SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public RegistrationPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By registerPage = By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']");
    private final By firstnameField = By.id("firstname");
    private final By lastnameField = By.id("lastname");
    private final By emailField = By.id("email_address");
    private final By passwordField = By.id("password");
    private final By confirmPassField = By.id("password-confirmation");
    private final By createButton = By.cssSelector("button.action.submit.primary");
    private final By completeMsg = By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private final By emptyFnameMsg = By.id("firstname-error");
    private final By emptyLnameMsg = By.id("lastname-error");
    private final By emptyEmailMsg = By.id("email_address-error");
    private final By emptyPassMsg = By.id("password-error");
    private final By emptyConfirmPassMsg = By.id("password-confirmation-error");
    private final By accountDropdown = By.cssSelector("div[class='panel header'] button[type='button']");
    private final By signOutButton = By.partialLinkText("Sign Out");


    public void openRegisterPage(){
        driver.element().click(registerPage);
    }

    public void setFirstname(String fname){
        windowManager.type(firstnameField, fname);
    }

    public void setLastname(String lname){
        windowManager.type(lastnameField, lname);
    }

    public void setEmail(String mail){
        windowManager.type(emailField, mail);
    }

    public void setPassword(String password){
        windowManager.type(passwordField, password);
    }

    public void setConfirmPass(String cPass){
        windowManager.type(confirmPassField, cPass);
    }

    public void clickCreate(){
        windowManager.click(createButton);
    }

    public String verifyProcess(){
        return windowManager.verifyText(completeMsg);

    }

    public String verifyEmptyFname(){
        return windowManager.verifyText(emptyFnameMsg);
    }

    public String verifyEmptyLname(){
        return windowManager.verifyText(emptyLnameMsg);
    }

    public String verifyEmailErrors(){
        return windowManager.verifyText(emptyEmailMsg);
    }

    public String verifyPassErrors(){
        return windowManager.verifyText(emptyPassMsg);
    }

    public String verifyConfirmPassErrors(){
        return windowManager.verifyText(emptyConfirmPassMsg);
    }

    public void signOut(){
        driver.element().click(accountDropdown).click(signOutButton);
    }







}
