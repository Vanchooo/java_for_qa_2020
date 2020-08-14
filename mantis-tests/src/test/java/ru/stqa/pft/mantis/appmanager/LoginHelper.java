package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {
    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void logIn(String username, String password) throws InterruptedException {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), username);
        click(By.cssSelector("input[type='Submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='Submit']"));
    }
}
