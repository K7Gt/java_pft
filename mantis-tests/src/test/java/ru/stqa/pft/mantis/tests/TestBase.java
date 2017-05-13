package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


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
    * Mantis - true
    * Bugify - false
    * */

    public boolean isIssueOpen(int issueId,boolean btCode) throws IOException, ServiceException {
        String status;

        if (btCode == true) {
            status = app.soap().getStatusOfIssueById(issueId).toLowerCase();
        } else status = app.rest().getStatusNameOfIssueById(issueId).toLowerCase();

        if(status.equals("resolved")){
            return false;
        }else return true;
    }

    public void skipIfNotFixed(int issueId,boolean btCode ) throws IOException, ServiceException {
        if (isIssueOpen(issueId, btCode)) {
            System.out.println("Ignored because of issue: " + issueId + " is still not resolved");
            throw new SkipException("Ignored because of issue " + issueId);

        }
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
