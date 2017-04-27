package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 25.04.2017.
 */
public class DeleteContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        List<ContactData> listOfContacts = new ArrayList<ContactData>(app.db().contacts());
        GroupData group =  new GroupData().withName("testNewGroup");
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
                .inGroup(group);

        app.gotTo().homePage();
        if(app.db().contacts().size() == 0){
            if(app.db().groups().size() == 0){
                app.gotTo().groupPage();
                app.group().create(group);
                app.gotTo().homePage();
                app.gotTo().addNewPage();
                app.contact().create(contact);
                app.gotTo().homePage();
            } else {
                app.gotTo().homePage();
                app.gotTo().addNewPage();
                app.contact().create(contact);
                app.gotTo().homePage();
            }
        } else{
            if(app.db().groups().size() == 0){
                app.gotTo().groupPage();
                app.group().create(group);
                app.gotTo().homePage();
                app.contact().contactToGroup(app.db().contacts().iterator().next());
            }else{
                for (int i = 0; i < listOfContacts.size(); i++) {
                    if(listOfContacts.get(i).getGroups().size() == 0){
                        app.contact().contactToGroup(listOfContacts.get(i));
                        break;
                    }
                }
            }
        }
    }

    @Test
    public void testDeleteContactFromGroup(){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.gotTo().homePage();
        for (GroupData gr: groups){
            app.contact().selectDeletedGroupFromList(gr);
            if(app.contact().all().size() != 0){
                break;
            }else {
                app.contact().contactCache = null;
            }
        }
        Contacts contacts = app.contact().all();
        ContactData initContact = app.db().contactById(contacts.iterator().next().getId());
        app.contact().deleteContactFromGroup(initContact);
        app.gotTo().homePage();

        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(initContact.getId());
        assertThat(after, equalTo(before.withOut(initContact).withAdded(contactFromDb)));
    }
}
