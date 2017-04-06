package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.gotTo().homePage();
        List<ContactData> before = app.contact().list();
        app.gotTo().addNewPage();
        ContactData contact = new ContactData("testname1", "testmiddlename",
                "testlastname", "test", "testtitle", "testcompany",
                "testaddressoftestcompany", "7777777", "7777777",
                "7777777", "1111111", "test@gmail.com", "test.com", "test1");
        app.contact().create(contact);
        app.gotTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }

}
