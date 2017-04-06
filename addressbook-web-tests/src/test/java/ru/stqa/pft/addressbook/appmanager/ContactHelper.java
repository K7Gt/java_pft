package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
        type(By.name("email"), contactData.getContactEmail());
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
    }

    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact,false);
        submitContactModification();
    }

    public void delete(int index) {
       selectContact(index);
       deleteSelectedContact();
       acceptAlertPopUp();
    }

    public void deleteSelectedContact() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//input[@name = 'selected[]']")).get(index).click();
    }


    public void acceptAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt = 'Edit']")).get(index).click();
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for(WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withContactName(firstName)
                    .withContactLastName(lastName)
                    .withContactCompanyAddress(address));
        }
        return contacts;
    }
}
