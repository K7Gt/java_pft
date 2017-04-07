package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.gotTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        // В случае проверки по id, в противном случае в конструкторе присваиваем максимальное значение и убираем из сравнения id
        //group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());  поиск максимального значения id при помощи компоратора и превращения списка в поток
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);

       /* Один и
       Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);*/
        Assert.assertEquals(before, after);

    }

}
