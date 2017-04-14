package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.gotTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().Count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
//         В случае проверки по id, в противном случае в конструкторе присваиваем максимальное значение и убираем из сравнения id
//        group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());  поиск максимального значения id при помощи компоратора и превращения списка в поток

       /* Один и
       Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void testBadGroupCreation() {
        app.gotTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.group().all();
        //assertThat(after.size(),equalTo(before.size()));
        assertThat(after, equalTo(before));

    }
}
