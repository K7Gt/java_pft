package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 08.04.2017.
 */
public class ContactAddressEmailTests extends TestBase {

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
                    .withContactEmail1("test1@gmail.com")
                    .withContactEmail2("test2@gmail.com")
                    .withContactEmail3("test3@gmail.com")
                    .withContactHomepage("test.com"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void testContactEmail(){
        app.gotTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getContactHomepage(), equalTo(contactInfoFormEditForm.getContactHomepage()));

    }

    @Test
    public void testContactAddress(){
        app.gotTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditForm)));

    }

    private String mergeEmails(ContactData contactInfoFormEditForm) {
        return Arrays.asList(contactInfoFormEditForm.getContactEmail1(),contactInfoFormEditForm.getContactEmail2(),contactInfoFormEditForm.getContactEmail3())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }
}
