package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 25.04.2017.
 */
public class AddContactToCroupTests extends TestBase {

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
               /* .inGroup(app.db().groups().size() == 0 ? group : app.db().groups().iterator().next())*/;
        app.gotTo().homePage();
        if(app.db().contacts().size() == 0){
           if(app.db().groups().size() == 0){
               app.gotTo().groupPage();
               app.group().create(group);
               app.gotTo().homePage();
               app.gotTo().addNewPage();
               app.contact().create(contact);
               app.gotTo().homePage();
           }else{
               app.gotTo().addNewPage();
               app.contact().create(contact);
               app.gotTo().homePage();
           }
        }else{
            if(app.db().groups().size() == 0){
                app.gotTo().groupPage();
                app.group().create(group);
                app.gotTo().homePage();
            }else{
                for (int i = 0; i < listOfContacts.size(); i++) {
                    if(listOfContacts.get(i).getGroups().size() != app.db().groups().size()){
                        break;
                    }else{
                        if(i == listOfContacts.size()-1){
                            if(listOfContacts.get(i).getGroups().size() != app.db().groups().size()){
                                break;
                            }else {
                                app.gotTo().groupPage();
                                app.group().create(new GroupData().withName("testNewGroup" + new Random().nextInt(10)+1));
                                app.gotTo().homePage();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testAddContactToCroup(){
        app.gotTo().homePage();

        Contacts before = app.db().contacts();

        ContactData initContact = null;
        List<ContactData> list = new ArrayList<ContactData>(before);
        for(int i = 0; i < list.size();i++){
            if(list.get(i).getGroups().size() != app.db().groups().size()){
                initContact = list.get(i);
                break;
            }
        }
        app.contact().contactToGroup(initContact);
        app.gotTo().homePage();

        Contacts after = app.db().contacts();

        ContactData contactFromDb = app.db().contactById(initContact.getId()) ;
        assertThat(after, equalTo(before.withOut(initContact).withAdded(contactFromDb)));
    }

}
