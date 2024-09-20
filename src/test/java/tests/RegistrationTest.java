package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.WindowManager;

import static org.testng.Assert.*;


public class RegistrationTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    RegistrationPage registerPage;
    WindowManager windowManager;


    @Test
    public void testRegisterWithValidCredentials(){
        registerPage.openRegisterPage();
        assertEquals(windowManager.verifyPageTitle(), "Create New Customer Account");
        registerPage.setFirstname(testData.getTestData("firstname"));
        registerPage.setLastname(testData.getTestData("lastname"));
        registerPage.setEmail(testData.getTestData("email"));
        registerPage.setPassword(testData.getTestData("password"));
        registerPage.setConfirmPass(testData.getTestData("password"));
        registerPage.clickCreate();
        assertTrue(registerPage.verifyProcess().contains("Thank you for registering"));
        registerPage.signOut();
        assertTrue(windowManager.verifyPageTitle().contains("You are signed out"));

    }

    @Test(priority = 1)
    public void testRegisterWithExistingCredentials(){
        registerPage.openRegisterPage();
        assertEquals(windowManager.verifyPageTitle(), "Create New Customer Account");
        registerPage.setFirstname(testData.getTestData("firstname"));
        registerPage.setLastname(testData.getTestData("lastname"));
        registerPage.setEmail(testData.getTestData("email"));
        registerPage.setPassword(testData.getTestData("password"));
        registerPage.setConfirmPass(testData.getTestData("password"));
        registerPage.clickCreate();
        assertTrue(registerPage.verifyProcess().contains("There is already an account with this email address."));
    }

    @Test(priority = 2)
    public void testRegisterWithEmptyFields(){
        registerPage.openRegisterPage();
        assertEquals(windowManager.verifyPageTitle(), "Create New Customer Account");
        registerPage.clickCreate();
        String requiredMsg = "This is a required field.";
        assertEquals(registerPage.verifyEmptyFname(), requiredMsg);
        assertEquals(registerPage.verifyEmptyLname(), requiredMsg);
        assertEquals(registerPage.verifyEmailErrors(), requiredMsg);
        assertEquals(registerPage.verifyPassErrors(), requiredMsg);
        assertEquals(registerPage.verifyConfirmPassErrors(), requiredMsg);
    }

    @Test(priority = 3)
    public void testRegisterWithUnMatchedPassword(){
        registerPage.openRegisterPage();
        assertEquals(windowManager.verifyPageTitle(), "Create New Customer Account");
        registerPage.setFirstname(testData.getTestData("firstname"));
        registerPage.setLastname(testData.getTestData("lastname"));
        registerPage.setEmail(testData.getTestData("email"));
        registerPage.setPassword(testData.getTestData("password"));
        registerPage.setConfirmPass(testData.getTestData("wrongPass"));
        registerPage.clickCreate();
        assertEquals(registerPage.verifyConfirmPassErrors(), "Please enter the same value again.");
    }

    @Test(priority = 4)
    public void testRegisterWithWrongEmailCriteria(){
        registerPage.openRegisterPage();
        assertEquals(windowManager.verifyPageTitle(), "Create New Customer Account");
        registerPage.setFirstname(testData.getTestData("firstname"));
        registerPage.setLastname(testData.getTestData("lastname"));
        registerPage.setEmail(testData.getTestData("wrongEmailCriteria"));
        registerPage.setPassword(testData.getTestData("password"));
        registerPage.setConfirmPass(testData.getTestData("password"));
        registerPage.clickCreate();
        assertEquals(registerPage.verifyEmailErrors(), "Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        registerPage = new RegistrationPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
