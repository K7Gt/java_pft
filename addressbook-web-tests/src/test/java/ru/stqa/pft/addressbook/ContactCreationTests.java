package ru.stqa.pft.addressbook;
import org.testng.annotations.Test;
public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        gotoAddNewPage();
        fillContactForm(new ContactDate("testname", "testmiddlename",
                "testlastname", "test", "testtitle", "testcompany",
                "testaddressoftestcompany", "7777777", "7777777",
                "7777777", "1111111", "test@gmail.com", "test.com"));
        submitContactCreation();
        gotoHomePage();
    }

}
