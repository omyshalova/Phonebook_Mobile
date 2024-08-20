package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthScreen;

public class LoginSecondTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthScreen(driver)
                .fillEmail("testolga@gmail.com")
                .fillPassword("Test1101!")
                .submitLogin()
                .isAccountOpen()
                .logout();
    }

    @Test
    public void loginSuccessModel(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("testolga@gmail.com").password("Test1101!")
                        .build())
                .submitLogin()
                .isAccountOpen()
                .logout();
    }

    @Test
    public void loginWrongEmail(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("testolgagmail.com").password("Test1101!")
                        .build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");

    }


}
