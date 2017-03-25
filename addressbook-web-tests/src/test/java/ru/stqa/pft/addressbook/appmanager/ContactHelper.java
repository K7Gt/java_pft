package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
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
            if(sizeOfList() > 1) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void deleteSelectedContact() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }

    public void selectContact() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }


    public void acceptAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public int sizeOfList() {
        List<WebElement> list = wd.findElements(By.xpath("html/body/div[1]/div[4]/form/select[5]/option"));
        return list.size();
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact,true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]"));
    }
}
