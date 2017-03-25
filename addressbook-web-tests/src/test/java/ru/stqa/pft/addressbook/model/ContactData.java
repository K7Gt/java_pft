package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String contactName;
    private final String contactMiddleName;
    private final String contactLastName;
    private final String contactNickname;
    private final String contactTitle;
    private final String contactCompany;
    private final String contactCompanyAddress;
    private final String contactHomePhone;
    private final String contactMobilePhone;
    private final String contactWorkPhone;
    private final String contactFax;
    private final String contactEmail;
    private final String contactHomepage;
    private String group;

    public ContactData(String contactName, String contactMiddleName,
                       String contactLastName, String contactNickname, String contactTitle,
                       String contactCompany, String contactCompanyAddress, String contactHomePhone,
                       String contactMobilePhone, String contactWorkPhone, String contactFax, String contactEmail, String contactHomepage, String group) {

        this.contactName = contactName;
        this.contactMiddleName = contactMiddleName;
        this.contactLastName = contactLastName;
        this.contactNickname = contactNickname;
        this.contactTitle = contactTitle;
        this.contactCompany = contactCompany;
        this.contactCompanyAddress = contactCompanyAddress;
        this.contactHomePhone = contactHomePhone;
        this.contactMobilePhone = contactMobilePhone;
        this.contactWorkPhone = contactWorkPhone;
        this.contactFax = contactFax;
        this.contactEmail = contactEmail;
        this.contactHomepage = contactHomepage;
        this.group = group;
    }

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

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactHomepage() {
        return contactHomepage;
    }

    public String getGroup() {
        return group;
    }
}
