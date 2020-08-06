package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    private String mobile;
    private String work;
    @Expose
    private String email;
    private String email2;
    private String email3;
    private String allPhones;
    private String allEmails;
    private File photo;


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

//    public ContactData(int id, String firstname, String lastname, String address, String homePhone, String email) {
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.email = email;
//    }
//
//    public ContactData(String firstname, String lastname, String address, String homePhone, String email, File photo) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.email = email;
//        this.photo = photo;
//    }
//
//    public ContactData(String firstname, String lastname, String address, String homePhone, String email) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.email = email;
//    }
//
//    public ContactData(int id, String firstname, String lastname, String address,
//                       String homePhone, String mobile, String work,
//                       String email, String email2, String email3, String allPhones, String allEmails) {
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.mobile = mobile;
//        this.work = work;
//        this.email = email;
//        this.email2 = email2;
//        this.email3 = email3;
//        this.allPhones = allPhones;
//        this.allEmails = allEmails;
//    }



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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return photo;
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
