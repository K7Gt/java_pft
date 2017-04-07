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
        app.gotTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("t1-withmodified")
                .withHeader("test7")
                .withFooter("test7");
        app.group().modify(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.group().all();

        before.remove(modifiedGroup);
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
        //assertThat(after, equalTo(before.withModified(modifiedGroup.getId(),group)));
    }
}
