package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {

    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo.png");

    ContactData contact = new ContactData().withFirstName("Ivan300433").withLastName("Ivanov12").withAddress("red square21")
            .withHomePhone("11224412").withEmail("ivanov1@test.ru").withPhoto(photo);

      app.contact().create(contact);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size() + 1);

      before.add(contact);
      assertEquals(before, after);

  }


}
