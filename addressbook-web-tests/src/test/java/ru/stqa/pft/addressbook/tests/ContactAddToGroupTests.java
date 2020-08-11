package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create
                    (new ContactData().withFirstName("IvanFirst").withLastName("Ivanov").withAddress("red square")
                            .withHomePhone("112244").withEmail("ivanov@test.ru"));
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

    @Test
    public void testContactDeleteFromGroup() throws Exception {

        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = new ContactData().withId(modifiedContact.getId()).withFirstName("IvanFirst322").withLastName("Ivanov").withAddress("red square")
                .withHomePhone("112244").withEmail("ivanov@test.ru").inGroup(group);
        app.goTo().contactPage();
        app.contact().create(newContact);
        app.contact().selectRightGroup(group);
        app.contact().selectContactById(newContact.getId());
        app.contact().clickRemoveFromGroup();
        Contacts after = app.db().contacts();

        assertEquals(after.size(), app.db().contacts().size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));

    }
}
