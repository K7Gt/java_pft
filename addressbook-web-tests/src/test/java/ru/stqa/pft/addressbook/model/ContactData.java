package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = 0;

    @Expose
    @Column(name = "firstname")
    private String contactName;

    @Expose
    @Column(name = "middlename")
    private String contactMiddleName;

    @Expose
    @Column(name = "lastname")
    private String contactLastName;

    @Expose
    @Column(name = "nickname")
    private String contactNickname;

    @Expose
    @Column(name = "title")
    private String contactTitle;

    @Expose
    @Column(name = "company")
    private String contactCompany;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String contactCompanyAddress;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String contactHomePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String contactMobilePhone;

    @Expose
    @Column(name = "work ")
    @Type(type = "text")
    private String contactWorkPhone;

    @Expose
    @Column(name = "fax ")
    @Type(type = "text")
    private String contactFax;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String contactEmail1;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String contactEmail2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String contactEmail3;

    @Expose
    @Column(name = "homepage")
    @Type(type = "text")
    private String contactHomepage;

    @Transient
    private String group;

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String fullName;
    @Transient
    private String contactSummary;

    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }



    public int getId() { return id; }

    public String getContactName() {
        return contactName;
    }

    public String getContactMiddleName() {
        return contactMiddleName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public String getContactNickname() {
        return contactNickname;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public String getContactCompany() {
        return contactCompany;
    }

    public String getContactCompanyAddress() {
        return contactCompanyAddress;
    }

    public String getContactHomePhone() {
        return contactHomePhone;
    }

    public String getContactMobilePhone() {
        return contactMobilePhone;
    }

    public String getContactWorkPhone() {
        return contactWorkPhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public String getContactEmail1() {
        return contactEmail1;
    }

    public String getContactHomepage() {
        return contactHomepage;
    }

    public String getGroup() {
        return group;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getContactEmail2() {
        return contactEmail2;
    }

    public String getContactEmail3() {
        return contactEmail3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public ContactData withContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
        return this;
    }

    public ContactData withContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    public ContactData withContactNickname(String contactNickname) {
        this.contactNickname = contactNickname;
        return this;
    }

    public ContactData withContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
        return this;
    }

    public ContactData withContactCompany(String contactCompany) {
        this.contactCompany = contactCompany;
        return this;
    }

    public ContactData withContactCompanyAddress(String contactCompanyAddress) {
        this.contactCompanyAddress = contactCompanyAddress;
        return this;
    }

    public ContactData withContactHomePhone(String contactHomePhone) {
        this.contactHomePhone = contactHomePhone;
        return this;
    }

    public ContactData withContactMobilePhone(String contactMobilePhone) {
        this.contactMobilePhone = contactMobilePhone;
        return this;
    }

    public ContactData withContactWorkPhone(String contactWorkPhone) {
        this.contactWorkPhone = contactWorkPhone;
        return this;
    }

    public ContactData withContactFax(String contactFax) {
        this.contactFax = contactFax;
        return this;
    }

    public ContactData withContactEmail1(String contactEmail) {
        this.contactEmail1 = contactEmail;
        return this;
    }

    public ContactData withContactEmail2(String contactEmail) {
        this.contactEmail2 = contactEmail;
        return this;
    }

    public ContactData withContactEmail3(String contactEmail) {
        this.contactEmail3 = contactEmail;
        return this;
    }

    public ContactData withContactHomepage(String contactHomepage) {
        this.contactHomepage = contactHomepage;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails){
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
        return this;
    }

    public ContactData withContactFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (contactMiddleName != null ? !contactMiddleName.equals(that.contactMiddleName) : that.contactMiddleName != null)
            return false;
        if (contactLastName != null ? !contactLastName.equals(that.contactLastName) : that.contactLastName != null)
            return false;
        if (contactNickname != null ? !contactNickname.equals(that.contactNickname) : that.contactNickname != null)
            return false;
        if (contactTitle != null ? !contactTitle.equals(that.contactTitle) : that.contactTitle != null) return false;
        if (contactCompany != null ? !contactCompany.equals(that.contactCompany) : that.contactCompany != null)
            return false;
        if (contactCompanyAddress != null ? !contactCompanyAddress.equals(that.contactCompanyAddress) : that.contactCompanyAddress != null)
            return false;
        if (contactHomePhone != null ? !contactHomePhone.equals(that.contactHomePhone) : that.contactHomePhone != null)
            return false;
        if (contactMobilePhone != null ? !contactMobilePhone.equals(that.contactMobilePhone) : that.contactMobilePhone != null)
            return false;
        if (contactWorkPhone != null ? !contactWorkPhone.equals(that.contactWorkPhone) : that.contactWorkPhone != null)
            return false;
        if (contactFax != null ? !contactFax.equals(that.contactFax) : that.contactFax != null) return false;
        if (contactEmail1 != null ? !contactEmail1.equals(that.contactEmail1) : that.contactEmail1 != null)
            return false;
        if (contactEmail2 != null ? !contactEmail2.equals(that.contactEmail2) : that.contactEmail2 != null)
            return false;
        if (contactEmail3 != null ? !contactEmail3.equals(that.contactEmail3) : that.contactEmail3 != null)
            return false;
        if (contactHomepage != null ? !contactHomepage.equals(that.contactHomepage) : that.contactHomepage != null)
            return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        return allEmails != null ? allEmails.equals(that.allEmails) : that.allEmails == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactMiddleName != null ? contactMiddleName.hashCode() : 0);
        result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
        result = 31 * result + (contactNickname != null ? contactNickname.hashCode() : 0);
        result = 31 * result + (contactTitle != null ? contactTitle.hashCode() : 0);
        result = 31 * result + (contactCompany != null ? contactCompany.hashCode() : 0);
        result = 31 * result + (contactCompanyAddress != null ? contactCompanyAddress.hashCode() : 0);
        result = 31 * result + (contactHomePhone != null ? contactHomePhone.hashCode() : 0);
        result = 31 * result + (contactMobilePhone != null ? contactMobilePhone.hashCode() : 0);
        result = 31 * result + (contactWorkPhone != null ? contactWorkPhone.hashCode() : 0);
        result = 31 * result + (contactFax != null ? contactFax.hashCode() : 0);
        result = 31 * result + (contactEmail1 != null ? contactEmail1.hashCode() : 0);
        result = 31 * result + (contactEmail2 != null ? contactEmail2.hashCode() : 0);
        result = 31 * result + (contactEmail3 != null ? contactEmail3.hashCode() : 0);
        result = 31 * result + (contactHomepage != null ? contactHomepage.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", contactMiddleName='" + contactMiddleName + '\'' +
                ", contactLastName='" + contactLastName + '\'' +
                ", contactNickname='" + contactNickname + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", contactCompany='" + contactCompany + '\'' +
                ", contactCompanyAddress='" + contactCompanyAddress + '\'' +
                ", contactHomePhone='" + contactHomePhone + '\'' +
                ", contactMobilePhone='" + contactMobilePhone + '\'' +
                ", contactWorkPhone='" + contactWorkPhone + '\'' +
                ", contactFax='" + contactFax + '\'' +
                ", contactEmail1='" + contactEmail1 + '\'' +
                ", contactEmail2='" + contactEmail2 + '\'' +
                ", contactEmail3='" + contactEmail3 + '\'' +
                ", contactHomepage='" + contactHomepage + '\'' +
                ", group='" + group + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

}
