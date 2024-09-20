package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

public class AddReviewTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    ReviewPage reviewPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    ProductPage productPage;
    WindowManager windowManager;

    @Test
    public void testAddProductReviewAsUser(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        reviewPage.addReview();
        reviewPage.rating();
        reviewPage.setSummary(testData.getTestData("summary"));
        reviewPage.setReview(testData.getTestData("review"));
        reviewPage.clickSubmit();
        assertEquals(registrationPage.verifyProcess(), "You submitted your review for moderation.");
        registrationPage.signOut();
    }

    @Test(priority = 1)
    public void testAddProductReviewAsGuest(){
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        reviewPage.addReview();
        reviewPage.setNickname(testData.getTestData("firstname"));
        reviewPage.rating();
        reviewPage.setSummary(testData.getTestData("summary"));
        reviewPage.setReview(testData.getTestData("review"));
        reviewPage.clickSubmit();
        assertEquals(registrationPage.verifyProcess(), "You submitted your review for moderation.");
    }

    @Test(priority = 2)
    public void testAddProductReviewWithEmptyFields(){
        productPage.hoverMenJackets();
        productPage.selectMontanaProduct();
        reviewPage.addReview();
        reviewPage.clickSubmit();
        assertEquals(reviewPage.verifyEmptyRating(), "Please select one of each of the ratings above.");
        assertEquals(reviewPage.verifyEmptyNickname(), "This is a required field.");
        assertEquals(reviewPage.verifyEmptySummary(), "This is a required field.");
        assertEquals(reviewPage.verifyEmptyReview(), "This is a required field.");
    }




    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        loginPage = new LoginPage(driver);
        reviewPage = new ReviewPage(driver);
        productPage = new ProductPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
