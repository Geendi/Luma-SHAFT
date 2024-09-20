package tests;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.*;

public class ChangePasswordTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    RegistrationPage registerPage;
    MyAccountPage myAccountPage;
    LoginPage loginPage;
    ChangePasswordPage passwordPage;
    WindowManager windowManager;

    @Test
    public void testChangePasswordWithValidData(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("password"));
        loginPage.clickSignIn();
        myAccountPage.openMyAccount();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        passwordPage.goToChangePass();
        assertEquals(windowManager.verifyPageTitle(), "Edit Account Information");
        passwordPage.setCurrentPass(testData.getTestData("password"));
        registerPage.setPassword(testData.getTestData("newPassword"));
        passwordPage.setConfirmPass(testData.getTestData("newPassword"));
        passwordPage.clickSave();
        assertEquals(registerPage.verifyProcess(), "You saved the account information.");
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("newPassword"));
        loginPage.clickSignIn();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        registerPage.signOut();
    }

    @Test(priority = 1)
    public void testLoginWithOldPassAfterChangeIt(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("newPassword"));
        loginPage.clickSignIn();
        myAccountPage.openMyAccount();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        passwordPage.goToChangePass();
        assertEquals(windowManager.verifyPageTitle(), "Edit Account Information");
        passwordPage.setCurrentPass(testData.getTestData("newPassword"));
        registerPage.setPassword(testData.getTestData("secondNewPass"));
        passwordPage.setConfirmPass(testData.getTestData("secondNewPass"));
        passwordPage.clickSave();
        assertEquals(registerPage.verifyProcess(), "You saved the account information.");
        assertEquals(windowManager.verifyPageTitle(), "Customer Login");
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("newPassword"));
        loginPage.clickSignIn();
        assertTrue(registerPage.verifyProcess().contains("The account sign-in was incorrect"));
    }

    @Test(priority = 2)
    public void testChangePasswordWithEmptyFields(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("secondNewPass"));
        loginPage.clickSignIn();
        myAccountPage.openMyAccount();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        passwordPage.goToChangePass();
        assertEquals(windowManager.verifyPageTitle(), "Edit Account Information");
        passwordPage.clickSave();
        assertEquals(passwordPage.verifyEmptyCurrentPass(), "This is a required field.");
        assertEquals(registerPage.verifyPassErrors(), "This is a required field.");
        assertEquals(registerPage.verifyConfirmPassErrors(), "This is a required field.");
        registerPage.signOut();
    }

    @Test(priority = 3)
    public void testChangePasswordWithWrongInputs(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("secondNewPass"));
        loginPage.clickSignIn();
        myAccountPage.openMyAccount();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        passwordPage.goToChangePass();
        assertEquals(windowManager.verifyPageTitle(), "Edit Account Information");
        passwordPage.setCurrentPass(testData.getTestData("newPassword"));
        registerPage.setPassword(testData.getTestData("secondNewPass"));
        passwordPage.setConfirmPass(testData.getTestData("secondNewPass"));
        passwordPage.clickSave();
        assertTrue(registerPage.verifyProcess().contains("The password doesn't match this account"));
        passwordPage.selectChangePassBox();
        passwordPage.setCurrentPass(testData.getTestData("secondNewPass"));
        registerPage.setPassword(testData.getTestData("password"));
        passwordPage.setConfirmPass(testData.getTestData("wrongPass"));
        passwordPage.clickSave();
        assertEquals(registerPage.verifyConfirmPassErrors(), "Please enter the same value again.");
        registerPage.signOut();
    }

    @Test(priority = 4)
    public void testChangePasswordWithWrongCriteria(){
        loginPage.openLoginPage();
        loginPage.setEmail(testData.getTestData("email"));
        loginPage.setPassword(testData.getTestData("secondNewPass"));
        loginPage.clickSignIn();
        myAccountPage.openMyAccount();
        assertEquals(windowManager.verifyPageTitle(), "My Account");
        passwordPage.goToChangePass();
        assertEquals(windowManager.verifyPageTitle(), "Edit Account Information");
        passwordPage.setCurrentPass(testData.getTestData("secondNewPass"));
        registerPage.setPassword(testData.getTestData("wrongPass"));
        passwordPage.setConfirmPass(testData.getTestData("wrongPass"));
        passwordPage.clickSave();
        assertEquals(registerPage.verifyPassErrors(),
                "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");
        registerPage.signOut();
    }






    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
        windowManager = new WindowManager(driver);
        registerPage = new RegistrationPage(driver);
        myAccountPage = new MyAccountPage(driver);
        loginPage = new LoginPage(driver);
        passwordPage = new ChangePasswordPage(driver);
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
