package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.gotTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.gotTo().addNewPage();
        ContactData contact = new ContactData()
                .withContactName("testname")
                .withContactMiddleName("testmiddlename")
                .withContactLastName("testlastname")
                .withContactNickname("test")
                .withContactTitle("testtitle")
                .withContactCompany("testcompany")
                .withContactCompanyAddress("testaddressoftestcompany")
                .withContactHomePhone("7777777")
                .withContactMobilePhone("7777777")
                .withContactWorkPhone("7777777")
                .withContactFax("1111111")
                .withContactEmail("test@gmail.com")
                .withContactHomepage("test.com")
                .withGroup("test1");
        app.contact().create(contact);
        app.gotTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        before.add(contact);
        Assert.assertEquals(before, after);


    }

}
