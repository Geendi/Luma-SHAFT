package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.SearchPage;
import pages.WindowManager;

import static org.testng.Assert.assertTrue;

public class SearchTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    WindowManager windowManager;
    SearchPage searchPage;
    ProductPage productPage;

    @Test
    public void testSearchForProduct(){
        searchPage.searchProduct(testData.getTestData("search"));
        assertTrue(windowManager.verifyPageTitle().contains(testData.getTestData("search")));
        assertTrue(productPage.verifyProductIsDisplayed());
    }

    @Test(priority = 1)
    public void testSearchSuggestionForProduct(){
        searchPage.searchUsingAutoSuggestion(testData.getTestData("searchSuggestion"));
        assertTrue(windowManager.verifyPageTitle().contains(testData.getTestData("search")));
    }



    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
