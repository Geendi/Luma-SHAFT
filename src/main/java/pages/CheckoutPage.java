package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public CheckoutPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By checkoutButton = By.cssSelector("button[data-role='proceed-to-checkout']");
    private final By shippingTitle = By.xpath("//div[normalize-space()='Shipping Address']");
    private final By emailField = By.xpath("//div[@class='control _with-tooltip']//input[@id='customer-email']");
    private final By firstnameField = By.xpath("//input[@name='firstname']");
    private final By lastnameField = By.xpath("//input[@name='lastname']");
    private final By streetField = By.xpath("//input[@name='street[0]']");
    private final By cityField = By.xpath("//input[@name='city']");
    private final By postalField = By.xpath("//input[@name='postcode']");
    private final By phoneField = By.xpath("//input[@name='telephone']");
    private final By shippingMethodBox = By.cssSelector("input[value='flatrate_flatrate']");
    private final By shippingMethodBoxSecond = By.cssSelector("input[value='tablerate_bestway']");
    private final By stateDropdown = By.xpath("//select[@name='region_id']");
    private final By countryDropdown = By.xpath("//select[@name='country_id']");
    private final By nextButton = By.xpath("//span[normalize-space()='Next']");
    private final By paymentTitle = By.xpath("//div[normalize-space()='Payment Method']");
    private final By placeOrderButton = By.xpath("//span[normalize-space()='Place Order']");
    private final By orderNum = By.cssSelector("//div[@class='checkout-success']//p[1]");
    private final By unSelectedShippingBoxMsg = By.xpath("//span[@data-bind='text: errorValidationMessage()']");
    private final By addressErrors = By.xpath("(//span[@data-bind='text: element.error'][normalize-space()='This is a required field.'])[1]");
    private final By cityErrors = By.xpath("(//span[@data-bind='text: element.error'][normalize-space()='This is a required field.'])[2]");
    private final By stateErrors = By.xpath("(//span[@data-bind='text: element.error'][normalize-space()='This is a required field.'])[3]");
    private final By postalErrors = By.xpath("(//span[@data-bind='text: element.error'][normalize-space()='This is a required field.'])[4]");
    private final By phoneErrors = By.xpath("(//span[@data-bind='text: element.error'][normalize-space()='This is a required field.'])[5]");



    public String verifyShippingPageTitle(){
        return windowManager.verifyText(shippingTitle);
    }

    public String verifyPaymentPageTitle(){
        return windowManager.verifyText(paymentTitle);
    }

    public String verifyUnSelectedShipping(){
        return windowManager.verifyText(unSelectedShippingBoxMsg);
    }

    public void clickCheckout(){
        windowManager.click(checkoutButton);
    }

    public void setEmail(String mail){
        windowManager.type(emailField, mail);
    }

    public void setFirstname(String fname){
        windowManager.type(firstnameField, fname);
    }

    public void setLastname(String lname){
        windowManager.type(lastnameField, lname);
    }

    public void setAddress(String street){
        windowManager.type(streetField, street);
    }

    public void setCity(String city){
        windowManager.type(cityField, city);
    }

    public void setPhone(String num){
        windowManager.type(phoneField, num);
    }

    public void setPostalCode(String code){
        windowManager.type(postalField, code);
    }

    public void selectShippingMethod(){
        windowManager.click(shippingMethodBox);
    }

    public void selectShippingMethod2(){
        windowManager.click(shippingMethodBoxSecond);
    }

    public void selectState(String text){
        driver.element().select(stateDropdown, text);
    }

    public void clickNext(){
        windowManager.click(nextButton);
    }

    public void clickPlaceOrder(){
        windowManager.click(placeOrderButton);
    }

    public String verifyOrderCompleted(){
        return windowManager.verifyText(orderNum);
    }

    public String verifyEmptyAddress(){
        return windowManager.verifyText(addressErrors);
    }

    public String verifyEmptyCity(){
        return windowManager.verifyText(cityErrors);
    }

    public String verifyEmptyState(){
        return windowManager.verifyText(stateErrors);
    }

    public String verifyEmptyPostal(){
        return windowManager.verifyText(postalErrors);
    }

    public String verifyEmptyPhone(){
        return windowManager.verifyText(phoneErrors);
    }


}
