package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Golem on 19.03.2017.
 */
public class ApplicationManager {

    private WebDriver wd;

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String fileFirefox = "c:\\Firefox_for_course\\firefox.exe";
    //private String fileChrome = "c:\\Java for Testing (Barancev)\\Others\\chromedriver.exe";
    //private String fileIE = "c:\\Java for Testing (Barancev)\\Others\\IEDriverServer32.exe";
    private String url = "http://localhost/addressbook/";
    private String login = "admin";
    private String password = "secret";
    private String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
    }


    public void init() {
        if(browser.equals(BrowserType.FIREFOX)){
            FirefoxBinary binary = new FirefoxBinary(new File(fileFirefox));
            wd = new FirefoxDriver(binary, new FirefoxProfile());
        } else if(browser.equals(BrowserType.CHROME)) {
           // System.setProperty("webdriver.chrome.driver", fileChrome);
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
           // System.setProperty("webdriver.ie.driver",fileIE);
            wd = new InternetExplorerDriver();

        }

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
