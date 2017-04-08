package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void ensurePreconditions(){
        app.gotTo().homePage();
        if(app.contact().all().size() == 0){
            app.gotTo().addNewPage();
            app.contact().create(new ContactData()
                    .withContactName("testname")
                    .withContactMiddleName("testmiddlename")
                    .withContactLastName("testlastname")
                    .withContactNickname("test")
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
        /*
        * Arrays.asList(1,2,3) создаем список из элем. 1-3
        * ..................................................
        * Превращаем его в поток .stream()
        * Фильтруем поток по не пустым строкоам .filter((s -> ! s.equals("")))  всё что не true выкидывается
        * ..................................................
        * Ко всем элементам потока применяем метод cleaned для этого
        * используем функцию .map() её назначение - применить ко всем элементам потока и вернуть поток состоящий из результатов функции cleaned()
        * внутрь .map() передаем функцию(можно как анонимную так и существующую) в качестве параметра для чего просто передаём её имя ContactPhonesTests::cleaned
        * функция cleaned() при этом должна быть глобальной static, в противном случае доступ к такой функции будет закрыт
        * ..................................................
        * Соединяем элементы списка в строку при помощи .collect() внутри которого вызывается коллектор Collectors
        * с методом .joining("\n") в котором указываем как соединять элементы списка
        * */
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
