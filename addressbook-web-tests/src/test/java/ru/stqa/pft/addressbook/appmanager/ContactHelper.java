package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public void fillContactForm(ContactData contactData, boolean creation){
        type(By.name("firstname"), contactData.getContactName());
        type(By.name("middlename"), contactData.getContactMiddleName());
        type(By.name("lastname"), contactData.getContactLastName());
        type(By.name("nickname"), contactData.getContactNickname());
        try {
            attach(By.name("photo"),contactData.getPhoto());
        } catch (NullPointerException e) {
        }
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
            if(contactData.getGroups().size() > 0 /*&& wd.findElements(By.xpath("html/body/div[1]/div[4]/form/select[5]/option[contains(text(),\'"
                    + contactData.getGroups().iterator().next().getGroupName() +"\')]")).size() != 0*/) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupName());
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
        String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");

        String title = wd.findElement(By.name("title")).getAttribute("value");
        String companyName = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();

        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String faxPhone = wd.findElement(By.name("fax")).getAttribute("value");

        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        String homePage = wd.findElement(By.name("homepage")).getAttribute("value");
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
                .withContactEmail3(email3)
                .withContactMiddleName(middleName)
                .withContactNickname(nickName)
                .withContactCompany(companyName)
                .withContactTitle(title)
                .withContactFax(faxPhone)
                .withContactHomepage(homePage);
    }

    public ContactData infoFromDetailsPage(ContactData contact) {
        initContactDetailsById(contact.getId());
        String contactSummary = wd.findElement(By.xpath("//div[@id = 'content']")).getText();
        wd.navigate().back();
        return new ContactData()
                .withContactSummary(contactSummary);


    }

    private void initContactDetailsById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='view.php?id=%s']",id))).click();
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

    public void selectContactById(int id) {
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

    public Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }
        int numRow = 2;
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
            String homePage ;
            if(isElementPresent(By.xpath("//tr["+numRow +"]/td[10]/a"))){
                homePage = cells.get(9).findElement(By.tagName("a")).getAttribute("href").replaceAll("http://","").replaceAll("/","");
            }else homePage = null;
            numRow++;
            //String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData()
                    .withId(id)
                    .withContactName(firstName)
                    .withContactLastName(lastName)
                    .withContactCompanyAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones)
                    .withContactHomepage(homePage));
//                    .withContactHomePhone(phones[0])
//                    .withContactMobilePhone(phones[1])
//                    .withContactWorkPhone(phones[2]));
        }
        return new Contacts(contactCache);
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']")).size();
    }

    public void deleteContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        click(By.name("remove"));
    }

    public void selectDeletedGroupFromList(GroupData group){
//        new Select(wd.findElement(By.xpath("//select[@name = 'group']"))).selectByValue(String.valueOf(group.getId()));
        new Select(wd.findElement(By.xpath("//select[@name = 'group']"))).selectByVisibleText(group.getGroupName());
    }

    public void contactToGroup(ContactData contact) {
        selectContactById(contact.getId());
        Set<String> groupsList = wd.findElements(By.xpath("//select[@name='to_group']/option")).stream().map((s) -> s.getText()).collect(Collectors.toSet());
        for (GroupData g : contact.getGroups()){
           String grName = g.getGroupName();
           groupsList.remove(grName);
        }
        new Select(wd.findElement(By.xpath("//select[@name = 'to_group']"))).selectByVisibleText(groupsList.iterator().next());
        click(By.xpath("//input[@name = 'add']"));


    }
}
