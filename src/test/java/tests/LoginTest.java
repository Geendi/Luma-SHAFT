package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.WindowManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    RegistrationPage registerPage;
    LoginPage loginPage;
    WindowManager windowManager;

    @Test
    public void testLoginWithValidCredentials(){
        loginPage.openLoginPage();
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        assertEquals(windowManager.verifyPageTitle(), "Home Page");
        registerPage.signOut();
        assertTrue(windowManager.verifyPageTitle().contains("You are signed out"));
    }

    @Test(priority = 1)
    public void testLoginWithEmptyFields(){
        loginPage.openLoginPage();
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.clickSignIn();
        String requiredMsg = "This is a required field.";
        assertEquals(loginPage.verifyEmailErrors(), requiredMsg);
        assertEquals(loginPage.verifyPassErrors(), requiredMsg);
    }

    @Test(priority = 2)
    public void testLoginWithUnregisteredEmail(){
        loginPage.openLoginPage();
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("wrongEmail"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        assertTrue(registerPage.verifyProcess().contains("The account sign-in was incorrect"));
    }

    @Test(priority = 3)
    public void testLoginWithWrongPassword(){
        loginPage.openLoginPage();
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("wrongPass"));
        loginPage.clickSignIn();
        assertTrue(registerPage.verifyProcess().contains("The account sign-in was incorrect"));
    }

    @Test(priority = 4)
    public void testLoginWithWrongEmailCriteria(){
        loginPage.openLoginPage();
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("wrongEmailCriteria"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        assertEquals(loginPage.verifyEmailErrors(), "Please enter a valid email address (Ex: johndoe@domain.com).");
    }




    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        registerPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
