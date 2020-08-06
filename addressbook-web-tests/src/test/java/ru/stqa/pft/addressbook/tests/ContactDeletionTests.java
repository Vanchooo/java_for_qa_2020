package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //File photo = new File("src/test/resources/photo.png");
        app.goTo().contactPage();
        if (app.contact().all().size() == 0){
            app.contact().create
                    (new ContactData().withFirstName("IvanFirst").withLastName("Ivanov").withAddress("red square")
                    .withHomePhone("112244").withEmail("ivanov@test.ru"));
        }
    }

    @Test(enabled = false)
    public void testDeleteAllContacts(){
        app.contact().selectAlL();
        //app.contact().deleteClick();
        app.contact().clickYesOnPopUP();
    }

    @Test
    public void testDeleteContact(){
        app.contact().returnToHomePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
