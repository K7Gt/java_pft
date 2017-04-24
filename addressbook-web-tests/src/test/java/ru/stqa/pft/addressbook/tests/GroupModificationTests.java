package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Golem on 19.03.2017.
 */
public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size() == 0) {
            app.gotTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1"));
            }
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("t1-withmodified")
                .withHeader("test7")
                .withFooter("test7");
        app.gotTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.db().groups();

        GroupData groupFromDb = app.db().groupById(group.getId());

        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(groupFromDb)));
        //assertThat(after, equalTo(before.withModified(modifiedGroup.getId(),group)));

        verifyGroupListUI();

    }

}
