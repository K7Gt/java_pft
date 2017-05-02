package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Golem on 02.05.2017.
 */
public class DbtestForMantis extends TestBase{

    @Test
    public void testDbMantis(){
        app.db().users();
    }

}
