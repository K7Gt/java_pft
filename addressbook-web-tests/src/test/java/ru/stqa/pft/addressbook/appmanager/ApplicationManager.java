package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Golem on 19.03.2017.
 */
public class ApplicationManager {

    private FirefoxDriver wd;

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String file = "c:\\Firefox_for_course\\firefox.exe";
    private String url = "http://localhost/addressbook/";
    private String login = "admin";
    private String password = "secret";


    public void init() {
        FirefoxBinary binary = new FirefoxBinary(new File(file));
        wd = new FirefoxDriver(binary, new FirefoxProfile());
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get(url);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(login, password);
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
