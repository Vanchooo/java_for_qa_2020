package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {

    Contacts before = app.contact().all();
    ContactData contact = new ContactData(
            "Ivan30043", "Ivanov12",
            "red square21", "11224412", "ivanov1@test.ru");
      app.contact().create(contact);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size() + 1);

      before.add(contact);
      assertEquals(before, after);

  }

}
