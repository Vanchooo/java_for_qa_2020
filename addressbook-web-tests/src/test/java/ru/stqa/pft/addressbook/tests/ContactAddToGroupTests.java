package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class ContactAddToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {

        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData newContact = new ContactData().withFirstName("IvanFirst322").withLastName("Ivanov").withAddress("red square")
                .withHomePhone("112244").withEmail("ivanov@test.ru").inGroup(groups.iterator().next());
        app.goTo().contactPage();
        app.contact().create(newContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() + 1);

        before.add(newContact);
        assertEquals(before, after);

    }
}
