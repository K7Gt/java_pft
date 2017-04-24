package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 19.03.2017.
 */
public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test: " + m.getName() + " with parameters " + Arrays.asList(p));
    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.info("Stop test: " + m.getName());
    }

    public void verifyGroupListUI() {
        if(Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getGroupName()))
                    .collect(Collectors.toSet())));
        }
    }
     public void verifyContactListUI() {
        if(Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();

            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withContactName(c.getContactName())
                            .withContactLastName(c.getContactLastName())
                            .withContactCompanyAddress(c.getContactCompanyAddress())
                            .withContactHomepage(c.getContactHomepage())

                            .withAllPhones(Arrays.asList(c.getContactHomePhone(),c.getContactMobilePhone(),c.getContactWorkPhone())
                                    .stream().filter((s -> ! s.equals("")))
                                    .map(s -> s.replaceAll("\\s","").replaceAll("[-()]",""))
                                    .collect(Collectors.joining("\n")))

                            .withAllEmails(Arrays.asList(c.getContactEmail1(),c.getContactEmail2(),c.getContactEmail3()).stream().filter((s -> ! s.equals("")))
                                    .collect(Collectors.joining("\n"))))
                    .collect(Collectors.toSet())));
        }
    }

}
