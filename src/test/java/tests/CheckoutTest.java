package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

public class CheckoutTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    CartPage cartPage;
    ProductPage productPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    CheckoutPage checkoutPage;
    WindowManager windowManager;

    @Test
    public void testCheckoutAsUserWithEmptyShippingFields(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        productPage.selectSize();
        productPage.selectColor();
        cartPage.addToCart();
        cartPage.openCartViaPopup();
        checkoutPage.clickCheckout();
        assertEquals(checkoutPage.verifyShippingPageTitle(), "Shipping Address");
        checkoutPage.clickNext();
        assertEquals(checkoutPage.verifyUnSelectedShipping(),
                "The shipping method is missing. Select the shipping method and try again.");
        checkoutPage.selectShippingMethod();
        checkoutPage.clickNext();
        assertEquals(checkoutPage.verifyEmptyAddress(), "This is a required field.");
        assertEquals(checkoutPage.verifyEmptyCity(), "This is a required field.");
        assertEquals(checkoutPage.verifyEmptyState(), "This is a required field.");
        assertEquals(checkoutPage.verifyEmptyPostal(), "This is a required field.");
        assertEquals(checkoutPage.verifyEmptyPhone(), "This is a required field.");
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
        cartPage.openCart();
        cartPage.deleteItem();
        registrationPage.signOut();
    }


    @Test(priority = 1)
    public void testCheckoutAsGuest(){
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        productPage.selectSize();
        productPage.selectColor();
        cartPage.addToCart();
        cartPage.openCartViaPopup();
        checkoutPage.clickCheckout();
        assertEquals(checkoutPage.verifyShippingPageTitle(), "Shipping Address");
        checkoutPage.setEmail(testData.getTestData("guestEmail"));
        checkoutPage.setFirstname(testData.getTestData("firstname"));
        checkoutPage.setLastname(testData.getTestData("lastname"));
        checkoutPage.setAddress(testData.getTestData("address"));
        checkoutPage.setCity(testData.getTestData("city"));
        checkoutPage.selectState(testData.getTestData("state"));
        checkoutPage.setPostalCode(testData.getTestData("code"));
        checkoutPage.setPhone(testData.getTestData("phone"));
        checkoutPage.selectShippingMethod();
        checkoutPage.clickNext();
        checkoutPage.clickPlaceOrder();
    }

    @Test(priority = 2)
    public void testCheckoutAsUser(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        productPage.selectSize();
        productPage.selectColor();
        cartPage.addToCart();
        cartPage.openCartViaPopup();
        checkoutPage.clickCheckout();
        assertEquals(checkoutPage.verifyShippingPageTitle(), "Shipping Address");
        checkoutPage.setAddress(testData.getTestData("address"));
        checkoutPage.setCity(testData.getTestData("city"));
        checkoutPage.selectState(testData.getTestData("state"));
        checkoutPage.setPostalCode(testData.getTestData("code"));
        checkoutPage.setPhone(testData.getTestData("phone"));
        checkoutPage.selectShippingMethod();
        checkoutPage.clickNext();
        checkoutPage.clickPlaceOrder();
        registrationPage.signOut();
    }



    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        productPage = new ProductPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
