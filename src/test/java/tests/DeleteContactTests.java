package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .submitLogin();
    }


    @Test
    public void deleteFirstContact() {
        new ContactListScreen(driver)
                .deleteFirstContact()
                .isListSizeLessThenOne();

    }

    @Test
    public void removeAllContactsSuccess(){
        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactsHere();
    }

}