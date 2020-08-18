package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
        //attach(By.name("photo"), contactData.getPhoto().getAbsoluteFile());

        if(contactData.getGroups().size() > 0){
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        }

    }

    public void selectRightGroup(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void selectRightGroupAddTo(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    public void submit(){ click(By.xpath("(//input[@name='submit'])[2]")); }

    public void submitModification(){ click(By.name("update")); }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectAlL() {click(By.id("MassCB"));}

    public void selectFirst() {click(By.name("selected[]"));}

    public void deleteSelectedContact() {click(By.xpath("//input[@value='Delete']"));}

    public void clickYesOnPopUP() {wd.switchTo().alert().accept();}


    public void clickEditContact(int index){
        wd.findElements(By.cssSelector("tr[name='entry']")).get(index).click();
    }

    public void clickEditContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
    }
    public void selectContactById(int id) {
        wd.findElement(By.id(String.valueOf(id))).click();
    }

    public void clickRemoveFromGroup() {
        click(By.xpath("//*[@id='content']/form[2]/div[3]/input"));
    }

    public void create(ContactData data){
        initContactCreation();
        fillContactForm(data);
        submit();
        returnToHomePage();
    }

    public void modify(ContactData contactData) {
        returnToHomePage();
        clickEditContactById(contactData.getId());
        fillContactForm(contactData);
        submitModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        clickYesOnPopUP();
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

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {

            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails);

            contacts.add(contact);
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        clickEditContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new ContactData().withId((contact.getId())).withFirstName(firstname).withLastName(lastname).withAddress(address)
                .withHomePhone(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3);

    }


    public Contacts returnContactsWithGroups(Contacts before) {
        Contacts contactsWithGroups = new Contacts();
        for(ContactData contact : before){
            if(contact.getGroups().size() != 0){
                contactsWithGroups.add(contact);
            }
        }
        return contactsWithGroups;
    }

    public Contacts returnContactsCanAddToGroup(Contacts contacts, Groups groups){
        Contacts contactsCanAddToGroup = new Contacts();
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() < groups.size()){
                contactsCanAddToGroup.add(contact);
            }
        }
        return contactsCanAddToGroup;
    }

    public void clickAddTo() {
        click(By.xpath("//*[@id='content']/form[2]/div[4]/input"));
    }
}
