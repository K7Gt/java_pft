package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;



public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupDate("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
