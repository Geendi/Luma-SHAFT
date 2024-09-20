package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.*;

public class WishListTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    WindowManager windowManager;
    ProductPage productPage;
    WishListPage wishListPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @Test
    public void testAddItemToWishListAsGuest(){
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        wishListPage.addToWishList();
        assertEquals(registrationPage.verifyProcess(), "You must login or register to add items to your wishlist.");
        registrationPage.openRegisterPage();
        registrationPage.setFirstname(testData.getTestData("firstname"));
        registrationPage.setLastname(testData.getTestData("lastname"));
        registrationPage.setEmail(testData.getTestData("email2"));
        registrationPage.setPassword(testData.getTestData("password"));
        registrationPage.setConfirmPass(testData.getTestData("password"));
        registrationPage.clickCreate();
        assertTrue(wishListPage.verifyItemDisplayed());
        wishListPage.deleteItem();
        assertEquals(wishListPage.verifyEmptyWishList(), "You have no items in your wish list.");
        registrationPage.signOut();
    }

    @Test(priority = 1)
    public void testAddItemToWishListAsUser(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        assertEquals(windowManager.verifyPageTitle(), "Montana Wind Jacket");
        wishListPage.addToWishList();
        assertTrue(registrationPage.verifyProcess().contains("Montana Wind Jacket has been added to your Wish List"));
        wishListPage.deleteItem();
        assertEquals(wishListPage.verifyEmptyWishList(), "You have no items in your wish list.");
        registrationPage.signOut();
    }


    @BeforeClass
    public void setUp() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        productPage = new ProductPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        wishListPage = new WishListPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
