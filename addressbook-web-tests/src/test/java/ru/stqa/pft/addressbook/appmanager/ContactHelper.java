package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Golem on 19.03.2017.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getContactname());
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
    }

    public void gotoAddNewPage() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }

    public void selectContact() {
        if (!checkSelection(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"))) {
            click(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
        }
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
}
