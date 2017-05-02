package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Golem on 01.05.2017.
 */
public class UsersHelper extends HelperBase {

   public UsersHelper(ApplicationManager app){
       super(app);
   }

   public void login(String login, String password){
       type(By.name("username"),login);
       type(By.name("password"),password);
       click(By.xpath("//input[@value ='Войти']"));
   }

    public List<Users>  all() {
       List<Users> list = new ArrayList<Users>();
        List<WebElement> rows = wd.findElements(By.xpath("//table[@class='table table-striped table-bordered table-condensed table-hover']/tbody/tr"));
        for(WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String name = cells.get(0).findElement(By.tagName("a")).getText();
            String eMail = cells.get(2).getText();

            list.add(new Users().withName(name).withEmail(eMail));
        }
        return list;
    }

    public Users selectUser(String name) {
        List<Users> usersList = all();
        Users user = null;
        for(Users u: usersList){
            if (u.getName().equals(name)){
                click(By.xpath(String.format("//a[.='%s']",name)));
                user = u;
                break;
            }
        }
        return user;
    }

    public void resetPassword() {
        click(By.xpath("//input[@value ='Сбросить пароль']"));
    }

    public void resetPasswordFromUser(String userName) {
        selectUser(userName);
        resetPassword();
    }
}
