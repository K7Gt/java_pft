package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletion extends TestBase {
    @Test
    public void ContactDeletion() {
        gotoHomePage();
        selectContact();
        deleteSelectedContact();
        acceptAlertPopUp();
        gotoHomePage();
    }

}
