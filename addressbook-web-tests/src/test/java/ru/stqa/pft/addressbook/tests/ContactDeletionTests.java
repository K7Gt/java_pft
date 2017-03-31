package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test
    public void ContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("testname", "testmiddlename",
                    "testlastname", "test", "testtitle", "testcompany",
                    "testaddressoftestcompany", "7777777", "7777777",
                    "7777777", "1111111", "test@gmail.com", "test.com", "test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        int before = app.getContactHelper().getCountContact();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlertPopUp();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getCountContact();
        Assert.assertEquals(after,before -1);

    }

}
