package ru.stqa.pft.addressbook.model;

public class GroupDate {
    private final String groupName;
    private final String groupHeader;
    private final String groupFooter;

    public GroupDate(String groupName, String groupHeader, String groupFooter) {
        this.groupName = groupName;
        this.groupHeader = groupHeader;
        this.groupFooter = groupFooter;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }
}
