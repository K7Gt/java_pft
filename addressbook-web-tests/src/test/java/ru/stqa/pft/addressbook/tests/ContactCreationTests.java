package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.gotoAddNewPage();
        app.fillContactForm(new ContactDate("testname", "testmiddlename",
                "testlastname", "test", "testtitle", "testcompany",
                "testaddressoftestcompany", "7777777", "7777777",
                "7777777", "1111111", "test@gmail.com", "test.com"));
        app.submitContactCreation();
        app.gotoHomePage();
    }

}
