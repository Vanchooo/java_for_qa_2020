package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
//      app.getContactHelper().initContactCreation();
//      app.getContactHelper().fillContactForm(new ContactData("IvanFirst", "Ivanov", "red square", "112244", "ivanov@test.ru"));
//      app.getContactHelper().submit();
      app.getContactHelper().createContact(new ContactData("IvanFirst", "Ivanov", "red square", "112244", "ivanov@test.ru"));
      app.getContactHelper().returnToGroupPage();
  }

}
