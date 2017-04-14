package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Golem on 14.04.2017.
 */
public class ContactDataGenerators {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts,file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format(
                    "%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                    ,contact.getContactName()
                    ,contact.getContactMiddleName()
                    ,contact.getContactLastName()
                    ,contact.getContactNickname()
                    ,contact.getContactTitle()
                    ,contact.getContactCompany()
                    ,contact.getContactCompanyAddress()
                    ,contact.getContactHomePhone()
                    ,contact.getContactMobilePhone()
                    ,contact.getContactWorkPhone()
                    ,contact.getContactFax()
                    ,contact.getContactEmail1()
                    ,contact.getContactEmail2()
                    ,contact.getContactEmail3()
                    ,contact.getContactHomepage()));
        }
        writer.close();

    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withContactName(String.format("contactName %s",i))
                    .withContactMiddleName(String.format("middleName %s",i))
                    .withContactLastName(String.format("lastName %s",i))
                    .withContactNickname(String.format("nickName %s",i))
                    .withContactTitle(String.format("title %s",i))
                    .withContactCompany(String.format("company %s",i))
                    .withContactCompanyAddress(String.format("companyAddress %s",i))
                    .withContactHomePhone(String.format("11111%s",i))
                    .withContactMobilePhone(String.format("22222%s",i))
                    .withContactWorkPhone(String.format("33333%s",i))
                    .withContactFax(String.format("44444%s",i))
                    .withContactEmail1(String.format("testEmail%s@gmail.com",i))
                    .withContactEmail2(String.format("testEmail%s@gmail.com",i))
                    .withContactEmail3(String.format("testEmail%s@gmail.com",i))
                    .withContactHomepage(String.format("homepage%s.com",i)));
        }
        return contacts;
    }
}
