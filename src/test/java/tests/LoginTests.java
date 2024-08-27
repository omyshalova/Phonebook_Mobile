package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthScreen(driver)
                .fillEmail("testolga@gmail.com")
                .fillPassword("Test1101!")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com").password("Test1101!").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    public void loginSuccessModel2(){
        Assert.assertTrue(new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("testolga@gmail.com").password("Test1101!").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));
    }


    @Test
    public void loginWrongEmail(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("testolgagmail.com").password("Mmar123456$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");

    }

    @Test
    public void loginWrongPassword(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("testolga@gmail.com").password("Mmar123").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");

    }


    @Test
    public void loginUnregisteredUser(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("till1@gmail.com").password("Ttill123456$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");

    }


    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}