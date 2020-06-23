package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testDeleteAllContacts(){
        app.getContactHelper().selectAlL();
        app.getContactHelper().deleteClick();
        app.getContactHelper().clickYesOnPopUP();
    }
}
