package ru.stqa.pft.mantis.tests;

import okio.Timeout;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void testChangePassword() throws InterruptedException, IOException, MessagingException {
        String newPassword = "password12345";

        app.login().logIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.usersControl().goToUsersControl();
        String userToChange = app.usersControl().selectRightUser();
        String email = app.usersControl().getEmail();
        app.usersControl().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationiLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(userToChange, newPassword));

    }

    private String findConfirmationiLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
