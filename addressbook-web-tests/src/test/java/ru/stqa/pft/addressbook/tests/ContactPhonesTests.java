package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 07.04.2017.
 */
public class ContactPhonesTests extends TestBase {

    @Test
    public void testContactPhone(){
        app.gotTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
//        assertThat(contact.getContactMobilePhone(), equalTo(cleaned(contactInfoFormEditForm.getContactMobilePhone())));
//        assertThat(contact.getContactWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getContactWorkPhone())));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getContactHomePhone(),contact.getContactMobilePhone(),contact.getContactWorkPhone())
                .stream().filter((s -> ! s.equals("")))
                .map(ContactPhonesTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
