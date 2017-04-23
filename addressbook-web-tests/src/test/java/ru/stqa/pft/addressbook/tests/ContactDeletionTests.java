package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
                    .withContactEmail1("test@gmail.com")
                    .withContactHomepage("test.com")
                    .withGroup("test1"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void ContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.gotTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
