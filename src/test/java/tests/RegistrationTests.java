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
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;

        boolean result = new AuthScreen(driver)
                .fillEmail("dex"+i+"@mail.com")
                .fillPassword("Ww12345$")
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(result);

    }
    @Test
    public void registrationSuccessModel(){
        int i = new Random().nextInt(1000)+1000;

        Auth auth = Auth.builder().email("dod"+i+"@mail.com").password("Dd12345$").build();

        boolean result = new AuthScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(result);
    }

    @Test
    public void registrationWrongEmail(){

        Auth auth = Auth.builder().email("dodmail.com").password("Dod123456$").build();

        new AuthScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistrationNegative()
                .isErrorMessageContainsText("must be a well-formed email address");

    }

    @Test
    public void registrationWrongPassword(){

        Auth auth = Auth.builder().email("dod@mail.com").password("Dd1234").build();

        new AuthScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistrationNegative()
                .isErrorMessageContainsText("At least 8 characters");

    }
    @Test
    public void registrationExistsUser(){

        Auth auth = Auth.builder().email("mara@gmail.com").password("Mmar123456$").build();

        new AuthScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistrationNegative()
                .isErrorMessageContainsText("User already exists");

    }
    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }
}