package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("mara@gmail.com")
                        .password("Mmar123456$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test(invocationCount = 3)
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("John")
                .lastName("Wow" + i)
                .email("aww" + i + "@gmail.com")
                .phone("54565489" + i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }


    @Test
    public void createContactSuccessReq() {

    }


    @Test
    public void createContactWithEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Wool")
                .email("awwa@gmail.com")
                .phone("545654894586")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");
    }


    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}