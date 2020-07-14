package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void modifyLastContact(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("IvanFirst", "Ivanov", "red square", "112244", "ivanov@test.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().returnToHomePage();
        app.getContactHelper().clickEditContact(before.size());
        ContactData contactData = new ContactData(before.get(before.size() - 1).getId(),"Ivan3004", "Ivanov1", "red square1", "1122441", "ivanov1@test.ru");
        app.getContactHelper().fillContactForm(contactData);
        app.getContactHelper().submitModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
