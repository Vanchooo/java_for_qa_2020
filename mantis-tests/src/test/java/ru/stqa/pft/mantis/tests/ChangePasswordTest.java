package ru.stqa.pft.mantis.tests;

import okio.Timeout;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ChangePasswordTest extends TestBase {

    @Test
    public void testChangePassword() throws InterruptedException {
        //app.login().logIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.login().logIn("123", "123");
        app.usersControl().goToUsersControl();


    }

}
