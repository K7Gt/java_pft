package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

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
    public void ContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.gotTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),index);

        before.remove(index);
        Assert.assertEquals(before,after);



    }
}
