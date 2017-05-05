package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Golem on 04.05.2017.
 */
public class TelnetTest extends TestBase {

    @Test
    public void testTelnet(){

        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain",now);
        String user = String.format("user1%s", now);
        String password = "password";

        app.james().createUser(user, password);

    }
}
