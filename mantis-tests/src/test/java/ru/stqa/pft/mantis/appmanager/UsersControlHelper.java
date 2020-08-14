package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsersControlHelper extends HelperBase {

    @FindBy(xpath = "//*[@id='sidebar']/ul/li[6]/a")
    private WebElement controlPage;

    @FindBy(xpath = "//*[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a")
    private WebElement usersControl;

    public UsersControlHelper(ApplicationManager app) {
        super(app);
    }
    public void goToUsersControl(){
        controlPage.click();
        usersControl.click();

    }
}
