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

public class ContactDeleteFromGroupTests extends TestBase {
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
    public void testContactDeleteFromGroup() throws Exception {

        Contacts before = app.db().contacts();
        Contacts contactsWithGroups = app.contact().returnContactsWithGroups(before);

        ContactData contact;
        Groups groupsBeforeModified;

        if(contactsWithGroups.size() == 0){
            contact = before.iterator().next();
            app.contact().selectContactById(contact.getId());
            app.contact().clickAddTo();
            before = app.db().contacts();
            contactsWithGroups = app.contact().returnContactsWithGroups(before);
            contact = contactsWithGroups.iterator().next();
            groupsBeforeModified = contact.getGroups();
            app.contact().returnToHomePage();
        } else {
            contact = contactsWithGroups.iterator().next();
            groupsBeforeModified = contact.getGroups();
        }

        GroupData groupToDeleteFrom = contact.getGroups().iterator().next();
        app.contact().selectRightGroup(groupToDeleteFrom);
        app.contact().selectContactById(contact.getId());
        app.contact().clickRemoveFromGroup();

        Contacts contactsWithId = app.db().contactsWithSpecificID(contact.getId());
        ContactData contactAfterModified = contactsWithId.iterator().next();
        Groups groupsAfterModified = contactAfterModified.getGroups();


        assertThat(groupsAfterModified, equalTo(groupsBeforeModified.without(groupToDeleteFrom)));


    }
}
