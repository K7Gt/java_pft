package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.gotTo().homePage();
        List<ContactData> before = app.contact().list();
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
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }

}
