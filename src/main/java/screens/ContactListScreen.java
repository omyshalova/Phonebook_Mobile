package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutBtn;

    public boolean isActivityTitleDisplayed(String text){
        //return activityTextView.getText().contains("Contact List");
        return isShouldHave(activityTextView,text,8);
    }

    public AuthScreen logout() {
        if (activityTextView.getText().contains("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthScreen(driver);
    }

    public ContactListScreen isAccountOpen() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }
}