package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {


    @Test
    public void registrationSuccess() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        int i = new Random().nextInt(1000);
        boolean result = new AuthScreen(driver)
                .fillEmail("testolga"+i+"@gmail.com")
                .fillPassword("Test1101!")
                .submitRegistration()
                .isNoContactsMessageDisplayed("No Contacts. Add One more!");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationSuccessModel() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        int i = new Random().nextInt(1000);
        boolean result = new AuthScreen(driver)
                .fillEmail("testolga"+i+"@gmail.com")
                .fillPassword("Test1101!")
                .submitRegistration()
                .isNoContactsMessageDisplayed("No Contacts. Add One more!");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationSuccessModel2(){
        int i = new Random().nextInt(1000);
        Assert.assertTrue(new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("testolga"+i+"@gmail.com").password("Test1101!")
                        .build())
                .submitRegistration()
                .isNoContactsMessageDisplayed("No Contacts. Add One more!"));
    }

    @Test
    public void registrationWrongEmail(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("testolgagmail.com").password("Test1101!")
                        .build())
                .submitRegistrationNegative()
                .isErrorMessageContainsText("{username=must be a well-formed email address}");
    }

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}