package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

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
    public void ContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.gotTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);



    }
}
