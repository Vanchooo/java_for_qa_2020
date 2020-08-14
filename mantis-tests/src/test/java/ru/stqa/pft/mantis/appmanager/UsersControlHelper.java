package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersControlHelper extends HelperBase {

    @FindBy(xpath = "//*[@id='sidebar']/ul/li[6]/a")
    public WebElement controlPage;

    @FindBy(xpath = "//*[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a")
    public WebElement usersControl;

    //List<String> userList;


    public UsersControlHelper(ApplicationManager app) {
        super(app);
        PageFactory.initElements(app.wd, this);
    }

    public void goToUsersControl(){

        controlPage.click();
        usersControl.click();

    }



    public String selectRightUser() {
        List<WebElement> userListTD = wd.findElements(By.xpath("//*[@id='main-container']/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[*]/td[1]"));
        List<String> userList = new ArrayList<>();
        for(WebElement user : userListTD){
            userList.add(user.getText());
        }

        userList.remove("administrator"); //чтобы случайно не поменять пароль администратуру
        String usrToChange = userList.iterator().next();

        wd.findElement(By.xpath("//*[contains(text(),'"+usrToChange+"')]")).click();

        return usrToChange;

    }

    public void resetPassword() {
        wd.findElement(By.xpath("//*[@id='manage-user-reset-form']/fieldset/span/input")).click();
    }

    public String getEmail() {
        String email = wd.findElement(By.xpath("//*[@id='email-field']")).getAttribute("value");
        return email;
    }
}
