package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String homePhone;
    private String mobile;
    private String work;
    private String email;


//    public ContactData withId(int id) {
//        this.id = id;
//        return this;
//    }
//
//    public ContactData withFirstName(String firstname) {
//        this.firstname = firstname;
//        return this;
//    }
//    public ContactData withLastName(String lastname) {
//        this.lastname = lastname;
//        return this;
//    }
//
//    public ContactData withAddress(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public ContactData withHomePhone(String homePhone) {
//        this.homePhone = homePhone;
//        return this;
//    }
//
//    public ContactData withMobile(String mobile) {
//        this.mobile = mobile;
//        return this;
//    }
//
//    public ContactData withWork(String work) {
//        this.work = work;
//        return this;
//    }

    public ContactData(int id, String firstname, String lastname, String address, String homePhone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homePhone = homePhone;
        this.email = email;
    }

    public ContactData(String firstname, String lastname, String address, String homePhone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homePhone = homePhone;
        this.email = email;
    }

    public ContactData(int id, String firstname, String lastname, String address,
                       String homePhone, String mobile, String work, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homePhone = homePhone;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
