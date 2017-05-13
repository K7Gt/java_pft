package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

/**
 * Created by Golem on 13.05.2017.
 */
public class SoapRestSkipTest extends TestBase {

//         Mantis - true
//         Bugify - false

    @Test(enabled = false)
    public void testMantisSoapApi() throws IOException, ServiceException {
        int issue= 0000001;

        skipIfNotFixed(issue,true);
        System.out.println("Test will be continued because issued: " + issue + " is resolved");
    }

    @Test
    public void testBugifyRestApi() throws IOException, ServiceException {
        int issue= 4;
        skipIfNotFixed(issue,false);
        System.out.println("Test will be continued because issued: " + issue + " is resolved");
    }
}
