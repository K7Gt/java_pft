package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("testname-change", "testmiddlename-change",
                "testlastnamechange-change", "test-change", "testtitle-change", "testcompany-change",
                "testaddressoftestcompany-change", "7777777", "7777777",
                "7777777", "1111111", "test-change@gmail.com", "test-change.com"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
