package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 07.04.2017.
 */
public class testContactPhones extends TestBase {

    @Test
    public void testContactPhone(){
        app.gotTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getContactHomePhone(), equalTo(cleaned(contactInfoFormEditForm.getContactHomePhone())));
        assertThat(contact.getContactMobilePhone(), equalTo(cleaned(contactInfoFormEditForm.getContactMobilePhone())));
        assertThat(contact.getContactWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getContactWorkPhone())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
