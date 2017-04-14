package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/getimg.jpeg");
        list.add(new Object[]{new ContactData().withContactName("testname1")
                .withContactLastName("testlastname1")
                .withContactCompanyAddress("test1-address-of-company")
                .withContactHomePhone("111")
                .withContactMobilePhone("222")
                .withContactWorkPhone("333")
                .withContactFax("444")
                .withContactEmail1("test1@gmail.com")
                .withContactEmail2("test2@gmail.com")
                .withContactEmail3("test3@gmail.com")
                .withContactHomepage("test1.com")
                .withPhoto(photo)});
        list.add(new Object[]{new ContactData().withContactName("testname2")
                .withContactLastName("testlastname2")
                .withContactCompanyAddress("test2-address-of-company")
                .withContactHomePhone("111")
                .withContactMobilePhone("222")
                .withContactWorkPhone("333")
                .withContactFax("444")
                .withContactEmail1("test1@gmail.com")
                .withContactEmail2("test2@gmail.com")
                .withContactEmail3("test3@gmail.com")
                .withContactHomepage("test2.com")
                .withPhoto(photo)});
        list.add(new Object[]{new ContactData().withContactName("testname3")
                .withContactLastName("testlastname3")
                .withContactCompanyAddress("test3-address-of-company")
                .withContactHomePhone("111")
                .withContactMobilePhone("222")
                .withContactWorkPhone("333")
                .withContactFax("444")
                .withContactEmail1("test1@gmail.com")
                .withContactEmail2("test2@gmail.com")
                .withContactEmail3("test3@gmail.com")
                .withContactHomepage("test3.com")
                .withPhoto(photo)});


        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.gotTo().homePage();
        Contacts before = app.contact().all();
        app.gotTo().addNewPage();
//        File photo = new File("src/test/resources/getimg.jpeg");
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
