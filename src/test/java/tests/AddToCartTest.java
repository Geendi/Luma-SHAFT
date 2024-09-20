package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.WindowManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    WindowManager windowManager;
    ProductPage productPage;
    CartPage cartPage;

    @Test
    public void tesAddItemToCart(){
        productPage.hoverMenJackets();
        assertEquals(windowManager.verifyPageTitle(), "Jackets");
        productPage.selectMontanaProduct();
        assertEquals(windowManager.verifyPageTitle(), "Montana Wind Jacket");
        productPage.selectSize();
        productPage.selectColor();
        cartPage.addToCart();
        assertTrue(productPage.verifyItemAddedToCart().contains("You added Montana Wind Jacket to your"));
        cartPage.openCart();
        assertEquals(cartPage.verifyItemInCart(), "Montana Wind Jacket");
        cartPage.deleteItem();
    }

    @Test(priority = 1)
    public void testAddItemToCartWithoutSelection(){
        productPage.hoverMenJackets();
        assertEquals(windowManager.verifyPageTitle(), "Jackets");
        productPage.selectMontanaProduct();
        assertEquals(windowManager.verifyPageTitle(), "Montana Wind Jacket");
        cartPage.addToCart();
        assertEquals(productPage.verifySizeRequiredMsg(), "This is a required field.");
        assertEquals(productPage.verifyColorRequiredMsg(), "This is a required field.");
    }

    @Test(priority = 2)
    public void testUpdateCart(){
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
        productPage.hoverMenJackets();
        assertEquals(windowManager.verifyPageTitle(), "Jackets");
        productPage.selectMontanaProduct();
        assertEquals(windowManager.verifyPageTitle(), "Montana Wind Jacket");
        productPage.selectSize();
        productPage.selectColor();
        cartPage.addToCart();
        assertTrue(productPage.verifyItemAddedToCart().contains("You added Montana Wind Jacket to your"));
        cartPage.openCart();
        assertEquals(cartPage.verifyItemInCart(), "Montana Wind Jacket");
        cartPage.changeQuantity("3");
        cartPage.updateCart();
        assertEquals(cartPage.verifyQuantity(), "3");
        cartPage.deleteItem();
        assertEquals(cartPage.verifyEmptyCart(), "You have no items in your shopping cart.");
    }


    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
