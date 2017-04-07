package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

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
                    .withContactHomePhone("111")
                    .withContactMobilePhone("2222")
                    .withContactWorkPhone("33333")
                    .withContactEmail("test@gmail.com")
                    .withContactHomepage("test.com"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void testContactCreation() {
        app.gotTo().homePage();
        Contacts before = app.contact().all();
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
        assertThat(app.contact().count(),equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


    }
    @Test
    public void testBadContactCreation() {
        app.gotTo().homePage();
        Contacts before = app.contact().all();
        app.gotTo().addNewPage();
        ContactData contact = new ContactData()
                .withContactName("'testname'");
        app.contact().create(contact);
        app.gotTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));


    }

}
