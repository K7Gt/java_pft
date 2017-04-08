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
public class ContactDetailsTests extends TestBase {

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
    public void testsContactDetails(){
        app.gotTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFormDetailsPage = app.contact().infoFromDetailsPage(contact);

        assertThat(cleaned(contactInfoFormDetailsPage.getContactSummary()), equalTo(mergeDetails(contactInfoFormEditForm)));
    }


    public String mergeDetails(ContactData contact){
        String result = Arrays.asList(
                contact.getContactName()
                ,contact.getContactMiddleName()
                ,contact.getContactLastName()
                ,contact.getContactNickname()

                ,contact.getContactTitle()
                ,contact.getContactCompany()
                ,contact.getContactCompanyAddress()

                ,contact.getContactHomePhone()
                ,contact.getContactMobilePhone()
                ,contact.getContactWorkPhone()
                ,contact.getContactFax()

                ,contact.getContactEmail1()
                ,contact.getContactEmail2()
                ,contact.getContactEmail3()

                ,contact.getContactHomepage())
                .stream().filter((s -> !s.equals("")))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining(""));
        return result;
    }

    public static String cleaned(String string){
        return string.replaceAll("\\s","")
                .replaceAll("[-()]","")
                .replaceAll("\n","")
                .replaceAll("H:","")
                .replaceAll("M:","")
                .replaceAll("W:","")
                .replaceAll("F:","")
                .replaceAll("Memberof:.*","")
                .replaceAll("Homepage:","");
    }
}
