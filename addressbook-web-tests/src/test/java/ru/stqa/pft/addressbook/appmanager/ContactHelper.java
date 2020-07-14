package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("home page"));
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void submit(){ click(By.xpath("(//input[@name='submit'])[2]")); }

    public void submitModification(){ click(By.name("update")); }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectAlL() {click(By.id("MassCB"));}

    public void selectFirst() {click(By.name("selected[]"));}

    public void deleteClick() {click(By.xpath("//input[@value='Delete']"));}

    public void clickYesOnPopUP() {wd.switchTo().alert().accept();}


    public void clickEditContact(int index){
        index = index + 1;
        wd.findElement(By.xpath("//*[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img")).click();
    }

    public void createContact(ContactData data){
        initContactCreation();
        fillContactForm(data);
        submit();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/*"));
        for (int i = 2; i <= elements.size(); i++) {

            int id = Integer.parseInt(wd.findElement(By.xpath("//*[@id='maintable']/tbody/tr["+i+"]/td[1]/*")).getAttribute("value"));
            String lastName = wd.findElement(By.xpath("//*[@id='maintable']/tbody/tr["+i+"]/td[2]")).getText();
            String firstName = wd.findElement(By.xpath("//*[@id='maintable']/tbody/tr["+i+"]/td[3]")).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
            contacts.add(contact);

            //*[@id="maintable"]/tbody/tr[2]/td[3]
            //*[@id="maintable"]/tbody/tr[3]/td[3]
            //*[@id="maintable"]/tbody/tr[4]/td[3]


        }
        return contacts;

    }
}
