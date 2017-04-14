package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
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
            File photo = new File("src/test/resources/getimg.jpeg");
            app.gotTo().addNewPage();
            app.contact().create(new ContactData()
                    .withContactName("testname")
                    .withContactMiddleName("testmiddlename")
                    .withContactLastName("testlastname")
                    .withContactNickname("test")
                    .withPhoto(photo)
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
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String split[] = line.split(";");
            list.add(new Object[]{new ContactData().withContactName(split[0])
                    .withContactMiddleName(split[1])
                    .withContactLastName(split[2])
                    .withContactNickname(split[3])
                    .withContactTitle(split[4])
                    .withContactCompany(split[5])
                    .withContactCompanyAddress(split[6])
                    .withContactHomePhone(split[7])
                    .withContactMobilePhone(split[8])
                    .withContactWorkPhone(split[9])
                    .withContactFax(split[10])
                    .withContactEmail1(split[11])
                    .withContactEmail2(split[12])
                    .withContactEmail3(split[13])
                    .withContactHomepage(split[14])});
            line = reader.readLine();
        }

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
