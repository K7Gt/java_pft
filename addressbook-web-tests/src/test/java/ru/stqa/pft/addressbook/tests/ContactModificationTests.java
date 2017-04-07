package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.gotTo().homePage();
        if(app.contact().all().size() == 0){
            app.gotTo().addNewPage();
            app.contact().create(new ContactData()
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
                    .withGroup("test1"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withContactName("testname-change")
                .withContactMiddleName("testmiddlename-change")
                .withContactLastName("testlastname-change")
                .withContactNickname("test-change")
                .withContactTitle("testtitle-change")
                .withContactCompany("testcompany-change")
                .withContactCompanyAddress("testaddressoftestcompany-change")
                .withContactHomePhone("77717777")
                .withContactMobilePhone("77717777")
                .withContactWorkPhone("77771777")
                .withContactFax("11111117")
                .withContactEmail("test-change@gmail.com")
                .withContactHomepage("test-change.com");
        app.contact().modify(contact);
        app.gotTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}
