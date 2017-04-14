package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

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
                    .withContactEmail1("test@gmail.com")
                    .withContactHomepage("test.com"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void testContactCreation() {
        app.gotTo().homePage();
        Contacts before = app.contact().all();
        app.gotTo().addNewPage();
        File photo = new File("src/test/resources/getimg.jpeg");
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
                .withContactEmail1("test@gmail.com")
                .withContactHomepage("test.com")
                .withPhoto(photo);
        app.contact().create(contact);
        app.gotTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


    }

  /*  @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/getimg.jpeg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
*/
    @Test(enabled = false)
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
