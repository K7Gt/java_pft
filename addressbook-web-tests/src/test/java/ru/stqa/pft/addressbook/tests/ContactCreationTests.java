package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.gotTo().homePage();
        if(app.db().contacts().size() == 0){
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
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>)xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.gotTo().homePage();
        Contacts before = app.db().contacts();
        app.gotTo().addNewPage();
//        File photo = new File("src/test/resources/getimg.jpeg");
        app.contact().create(contact);
        app.gotTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

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
        Contacts before = app.db().contacts();
        app.gotTo().addNewPage();
        ContactData contact = new ContactData()
                .withContactName("'testname'");
        app.contact().create(contact);
        app.gotTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before));
    }

}
