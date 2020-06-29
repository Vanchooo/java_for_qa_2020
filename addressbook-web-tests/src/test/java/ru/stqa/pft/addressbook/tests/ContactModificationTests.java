package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void modifyFirstContact(){
        app.getContactHelper().clickEditFirstContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan1", "Ivanov1", "red square1", "1122441", "ivanov1@test.ru"));
        app.getContactHelper().submitModification();
    }
}
