package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testDeleteAllContacts(){
        app.getContactHelper().selectAlL();
        app.getContactHelper().deleteClick();
        app.getContactHelper().clickYesOnPopUP();
    }

    @Test
    public void testDeleteFirstContact(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("IvanFirst", "Ivanov", "red square", "112244", "ivanov@test.ru"));
        }
        app.getContactHelper().returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectFirst();
        app.getContactHelper().deleteClick();
        app.getContactHelper().clickYesOnPopUP();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);

        Assert.assertEquals(before, after);

    }
}
