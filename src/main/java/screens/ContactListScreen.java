package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@content-desc='More options']")
    List<AndroidElement> menuOptionsList;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;
    @FindBy(id = "android:id/button1")
    AndroidElement OkBtn;
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHere;

    int countBefore;
    int countAfter;


    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }

    public AuthScreen logout2() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }

    public AuthScreen logout3() {
        if (isElementPresentInList(menuOptionsList)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public boolean isContactListActivityDisplayed() {
        return isShouldHave(activityTextView, "Contact list", 15);
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list")) {
            should(plusBtn, 5);
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name,String lastName){
        // List<AndroidElement> list =  driver.findElements(By.xpath(""));
        isShouldHave(activityTextView,"Contact list",5);
        System.out.println("size of list " +contactNameList.size());
        boolean isPresent=false;

        for (AndroidElement el: contactNameList) {
            if(el.getText().equals(name + " "+lastName)){
                isPresent = true;
                break;
            }
        }

        Assert.assertTrue(isPresent);

        return this;
    }
    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        countBefore = contactList.size();
        System.out.println(countBefore);
        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom=rectangle.getX()+ rectangle.getWidth()/8;
        //int xTo= rectangle.getX()+(rectangle.getWidth()/8)*7;
        int xTo = rectangle.getWidth()-xFrom;
        int y=rectangle.getY()+rectangle.getHeight()/2;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom,y)).moveTo(PointOption.point(xTo,y)).release().perform();
        should(OkBtn, 10);
        OkBtn.click();
        int countAfter = contactList.size();
        System.out.println(countAfter);
        return this;
    }

    public ContactListScreen isListSizeLessThenOne() {
        Assert.assertEquals(countBefore-countAfter, 1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contactList.size()>0){
            deleteFirstContact();
            pause(1000);
        }
        return this;
    }

    public ContactListScreen isNoContactsHere() {
        isShouldHave(noContactsHere, "No Contacts. Add One More!",10 );
        return this;
    }
}