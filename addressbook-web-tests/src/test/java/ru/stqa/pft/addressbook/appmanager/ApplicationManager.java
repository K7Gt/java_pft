package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Golem on 19.03.2017.
 */
public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

        if(browser.equals(BrowserType.FIREFOX)){
            FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("browser.firefoxFolder")));
            wd = new FirefoxDriver(binary, new FirefoxProfile());
            wd.manage().window().maximize();
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        } else if(browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        } else if (browser.equals(BrowserType.IE)){
            wd = new InternetExplorerDriver();
            wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        }
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper gotTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
