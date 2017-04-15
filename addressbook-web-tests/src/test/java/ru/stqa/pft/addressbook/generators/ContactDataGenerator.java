package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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
public class ContactDataGenerator {

    @Parameter(names = "-c",description = "GroupCount")
    public int count;

    @Parameter(names = "-f",description = "Target file")
    public String file;

    @Parameter(names = "-d",description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }else if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)){
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
        }
    }

    private List<ContactData> generateContacts(int count) {
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
