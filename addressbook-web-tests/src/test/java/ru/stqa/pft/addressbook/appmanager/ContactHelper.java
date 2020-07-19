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
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {

            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null);

            contacts.add(contact);
        }
        return contacts;
    }
}
