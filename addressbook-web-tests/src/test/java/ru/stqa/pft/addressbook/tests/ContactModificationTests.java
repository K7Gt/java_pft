package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.gotTo().homePage();
        if(app.contact().list().size() == 0){
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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId())
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
        app.contact().modify(index, contact);
        app.gotTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(),after.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
