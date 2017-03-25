package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("testname", "testmiddlename",
                    "testlastname", "test", "testtitle", "testcompany",
                    "testaddressoftestcompany", "7777777", "7777777",
                    "7777777", "1111111", "test@gmail.com", "test.com", "test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("testname-change", "testmiddlename-change",
                "testlastnamechange-change", "test-change", "testtitle-change", "testcompany-change",
                "testaddressoftestcompany-change", "77717777", "77717777",
                "77771777", "11117111", "test-change@gmail.com", "test-change.com", null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
