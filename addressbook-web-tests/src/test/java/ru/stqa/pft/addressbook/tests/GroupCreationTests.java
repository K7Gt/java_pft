package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>)xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.gotTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().Count(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
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
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.db().groups();
        //assertThat(after.size(),equalTo(before.size()));
        assertThat(after, equalTo(before));

    }
}
