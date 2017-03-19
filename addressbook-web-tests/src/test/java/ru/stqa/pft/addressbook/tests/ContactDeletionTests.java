package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void ContactDeletion() {
        app.gotoHomePage();
        app.selectContact();
        app.deleteSelectedContact();
        app.acceptAlertPopUp();
        app.gotoHomePage();
    }

}
