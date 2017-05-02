package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Golem on 01.05.2017.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app){
        super(app);
    }

    public void usersManagePage() {
       click(By.xpath("//span[contains(text(),'управление')]"));
       click(By.xpath("//a[contains(text(),'Управление пользователями')]"));
    }
}
