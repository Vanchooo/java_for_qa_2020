package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver wb;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wb = new ChromeDriver();
        wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login("admin", "secret");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wb.quit();

    }

    protected void initGroupCreation() {
        wb.findElement(By.name("new")).click();
    }

    private void login(String username, String password) {
        wb.get("http://localhost/addressbook/group.php");
        wb.findElement(By.name("user")).click();
        wb.findElement(By.name("user")).clear();
        wb.findElement(By.name("user")).sendKeys(username);
        wb.findElement(By.name("pass")).click();
        wb.findElement(By.name("pass")).clear();
        wb.findElement(By.name("pass")).sendKeys(password);
        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage() {
        wb.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
        wb.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
        wb.findElement(By.name("group_name")).click();
        wb.findElement(By.name("group_name")).clear();
        wb.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wb.findElement(By.name("group_header")).click();
        wb.findElement(By.name("group_header")).clear();
        wb.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wb.findElement(By.name("group_footer")).click();
        wb.findElement(By.name("group_footer")).clear();
        wb.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void gotoGroupPage() {
        wb.findElement(By.linkText("groups")).click();
    }

    private boolean isElementPresent(By by) {
        try {
            wb.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wb.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void deleteSelectedGroups() {
        wb.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
        wb.findElement(By.name("selected[]")).click();
    }
}
