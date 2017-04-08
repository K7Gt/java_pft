package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = 0;
    private String contactName;
    private String contactMiddleName;
    private String contactLastName;
    private String contactNickname;
    private String contactTitle;
    private String contactCompany;
    private String contactCompanyAddress;
    private String contactHomePhone;
    private String contactMobilePhone;
    private String contactWorkPhone;
    private String contactFax;
    private String contactEmail1;
    private String contactEmail2;
    private String contactEmail3;
    private String contactHomepage;
    private String group;
    private String allPhones;
    private String allEmails;

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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactLastName='" + contactLastName + '\'' +
                ", contactCompanyAddress='" + contactCompanyAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (contactLastName != null ? !contactLastName.equals(that.contactLastName) : that.contactLastName != null)
            return false;
        return contactCompanyAddress != null ? contactCompanyAddress.equals(that.contactCompanyAddress) : that.contactCompanyAddress == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
        result = 31 * result + (contactCompanyAddress != null ? contactCompanyAddress.hashCode() : 0);
        return result;
    }


}
