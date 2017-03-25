package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Created by Golem on 19.03.2017.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

    public void gotoAddNewPage() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        } else {
            click(By.xpath("html/body/div[1]/div[3]/ul/li[1]/a"));
        }

    }
}
