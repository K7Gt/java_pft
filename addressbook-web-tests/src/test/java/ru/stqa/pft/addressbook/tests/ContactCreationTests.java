package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getCountContact();
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("testname", "testmiddlename",
                "testlastname", "test", "testtitle", "testcompany",
                "testaddressoftestcompany", "7777777", "7777777",
                "7777777", "1111111", "test@gmail.com", "test.com", "test1"));
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getCountContact();
        Assert.assertEquals(after,before + 1);

    }

}
