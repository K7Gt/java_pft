package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * Created by Golem on 19.03.2017.
 */
public class TestBase {
//    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
//        app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"),"config_defaults_inc.php","config_defaults_inc.php.bak");

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
//        app.ftp().restore("config_defaults_inc.php.bak","config_defaults_inc.php");
        app.stop();
    }

/*
    @BeforeMethod
    public void logOn(Method method){
        logger.info("Start test " + method.getName());

    }
    @AfterMethod(alwaysRun = true)
    public void logOff(Method method){
        logger.info("Stop test " + method.getName());
    }
*/

}
