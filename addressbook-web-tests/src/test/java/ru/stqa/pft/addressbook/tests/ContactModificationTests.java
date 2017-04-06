package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.gotTo().homePage();
        if(app.contact().list().size() == 0){
            app.gotTo().addNewPage();
            app.contact().create(new ContactData("testname", "testmiddlename",
                    "testlastname", "test", "testtitle", "testcompany",
                    "testaddressoftestcompany", "7777777", "7777777",
                    "7777777", "1111111", "test@gmail.com", "test.com", "test1"));
            app.gotTo().homePage();
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(),"t-change", "testmiddlename-change",
                "t1", "test-change", "testtitle-change", "t1",
                "testaddressoftestcompany-change", "77717777", "77717777",
                "77771777", "11117111", "test-change@gmail.com", "test-change.com", null);
        app.contact().modify(index, contact);
        app.gotTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(),after.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
