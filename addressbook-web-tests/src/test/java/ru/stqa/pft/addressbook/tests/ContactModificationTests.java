package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        //File photo = new File("src/test/resources/photo.png");
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create
                    (new ContactData().withFirstName("IvanFirst").withLastName("Ivanov").withAddress("red square")
                    .withHomePhone("112244").withEmail("ivanov@test.ru"));
        }
    }

    @Test
    public void modifyContact(){

        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Ivan300433").withLastName("Ivanov12").withAddress("red square21")
                .withHomePhone("11224412").withEmail("ivanov1@test.ru");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();

        assertEquals(after.size(), app.db().contacts().size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


    }


}
