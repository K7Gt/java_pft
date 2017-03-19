package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

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


    public void fillContactForm(ContactDate contactDate) {
        type(By.name("firstname"), contactDate.getContactname());
        type(By.name("middlename"), contactDate.getContactMiddleName());
        type(By.name("lastname"), contactDate.getContactLastName());
        type(By.name("nickname"), contactDate.getContactNickname());
        type(By.name("title"), contactDate.getContactTitle());
        type(By.name("company"), contactDate.getContactCompany());
        type(By.name("address"), contactDate.getContactCompanyAddress());
        type(By.name("home"), contactDate.getContactHomePhone());
        type(By.name("mobile"), contactDate.getContactMobilePhone());
        type(By.name("work"), contactDate.getContactWorkPhone());
        type(By.name("fax"), contactDate.getContactFax());
        type(By.name("email"), contactDate.getContactEmail());
        type(By.name("homepage"), contactDate.getContactHomepage());
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
}
