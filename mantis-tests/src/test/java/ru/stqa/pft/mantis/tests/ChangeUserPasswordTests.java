package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

/**
 * Created by Golem on 01.05.2017.
 */
public class ChangeUserPasswordTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        app.mail().start();
        int uniquePrefix = new Random().nextInt(10);
        Users newUser = new Users().withName(String.format("name%s", uniquePrefix))
                .withEmail(String.format("testEmail%s.@test.test",uniquePrefix))
                .withPassword(String.format("password%s", uniquePrefix));
        if(app.db().users().size() < 2){
            app.registration().start(newUser.getName(), newUser.geteMail());
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, newUser.geteMail());
            app.registration().finish(confirmationLink, newUser.getPassword());
        }
    }

    @Test
    public void testChangeUserPassword() throws IOException, MessagingException {
        String newPassword = String.format("newpassword%s",new Random().nextInt(10));

        app.user().login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
        app.goTo().usersManagePage();
        Users user1 = app.user().resetPasswordFromUser("wrongName");
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user1.geteMail());
        app.registration().finish(confirmationLink, newPassword);
        HttpSession session = app.newSession();
        System.out.println(user1.getName() + "  " + user1.getPassword());
        assertTrue(session.login(user1.getName(),newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }

    @AfterMethod
    public void stopMailServer(){
        app.mail().stop();
    }

}
