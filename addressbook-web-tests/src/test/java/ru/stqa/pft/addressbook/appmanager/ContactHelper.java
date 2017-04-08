package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getContactName());
        type(By.name("middlename"), contactData.getContactMiddleName());
        type(By.name("lastname"), contactData.getContactLastName());
        type(By.name("nickname"), contactData.getContactNickname());
        type(By.name("title"), contactData.getContactTitle());
        type(By.name("company"), contactData.getContactCompany());
        type(By.name("address"), contactData.getContactCompanyAddress());
        type(By.name("home"), contactData.getContactHomePhone());
        type(By.name("mobile"), contactData.getContactMobilePhone());
        type(By.name("work"), contactData.getContactWorkPhone());
        type(By.name("fax"), contactData.getContactFax());
        type(By.name("email"), contactData.getContactEmail1());
        type(By.name("email2"), contactData.getContactEmail2());
        type(By.name("email3"), contactData.getContactEmail3());
        type(By.name("homepage"), contactData.getContactHomepage());
        if(creation){
            if(getSizeOfList() > 1 && wd.findElements(By.xpath("html/body/div[1]/div[4]/form/select[5]/option[contains(text(),\'" + contactData.getGroup() +"\')]")).size() != 0) {
                new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void create(ContactData contact) {
        fillContactForm(contact,true);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        contactCache = null;
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']",id))).click();
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withContactName(firstName)
                .withContactLastName(lastName)
                .withContactCompanyAddress(address)
                .withContactHomePhone(homePhone)
                .withContactMobilePhone(mobilePhone)
                .withContactWorkPhone(workPhone)
                .withContactEmail1(email1)
                .withContactEmail2(email2)
                .withContactEmail3(email3);
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteSelectedContact();
        contactCache = null;
        acceptAlertPopUp();
    }


    public void deleteSelectedContact() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.xpath("//input[@value = '" + id + "']")).click();
    }


    public void acceptAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public int getSizeOfList() {
        int size = wd.findElements(By.xpath("html/body/div[1]/div[4]/form/select[5]/option")).size();
        return  size;

    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]"));
    }

    Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for(WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).findElements(By.tagName("a"))
                    .stream().map(WebElement::getText)
                    .filter((s -> !s.equals("")))
                    .collect(Collectors.joining("\n"));
            String allPhones = cells.get(5).getText();
            //String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData()
                    .withId(id)
                    .withContactName(firstName)
                    .withContactLastName(lastName)
                    .withContactCompanyAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
//                    .withContactHomePhone(phones[0])
//                    .withContactMobilePhone(phones[1])
//                    .withContactWorkPhone(phones[2]));
        }
        return new Contacts(contactCache);
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']")).size();
    }

}
