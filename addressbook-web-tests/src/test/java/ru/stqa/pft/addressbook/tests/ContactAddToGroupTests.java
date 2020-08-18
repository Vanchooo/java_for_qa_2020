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

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        Contacts contactsCanAddToGroup = app.contact().returnContactsCanAddToGroup(contacts, groups);

        ContactData contact = new ContactData();

        if(contactsCanAddToGroup.size() == 0){
            app.goTo().contactPage();
            app.contact().create
                    (new ContactData().withFirstName("IvanFirst").withLastName("Ivanov").withAddress("red square")
                            .withHomePhone("112244").withEmail("ivanov@test.ru"));
            contacts = app.db().contacts();
            contactsCanAddToGroup = app.contact().returnContactsCanAddToGroup(contacts, groups);
            contact = contactsCanAddToGroup.iterator().next();

        }else {
            contact = contactsCanAddToGroup.iterator().next();
        }

        Groups contactGroupsBeforeModified = contact.getGroups();
        GroupData groupToAddTo = app.group().returnGroupToAddTo(contact.getGroups(), groups);

        app.contact().selectContactById(contact.getId());
        app.contact().selectRightGroupAddTo(groupToAddTo);
        app.contact().clickAddTo();

        Contacts contactsWithId = app.db().contactsWithSpecificID(contact.getId());
        ContactData contactAfterModified = contactsWithId.iterator().next();
        Groups groupsAfterModified = contactAfterModified.getGroups();

        assertThat(groupsAfterModified, equalTo(contactGroupsBeforeModified.withAdded(groupToAddTo)));

    }

}
