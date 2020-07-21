package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testAddNewContact() throws Exception {

      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contactData = new ContactData("IvanFirst157", "Ivanov", "red square", "112244", "ivanov@test.ru");
      app.getContactHelper().createContact(contactData);
      app.getContactHelper().returnToGroupPage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(contactData);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);


  }

}
