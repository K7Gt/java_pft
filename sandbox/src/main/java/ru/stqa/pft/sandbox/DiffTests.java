package ru.stqa.pft.sandbox;

/**
 * Created by Golem on 08.04.2017.
 */
public class DiffTests {

    public static void main(String[] args){
        String test = "testname testMiddlename testlastname\n" +
                "testNick\n" +
                "testtitle\n" +
                "testCompany\n" +
                "testaddres\n" +
                "\n" +
                "H: 111\n" +
                "M: 2222\n" +
                "W: 33333\n" +
                "\n" +
                "test1@gmail.com\n" +
                "test2@gmail.com\n" +
                "test3@gmail.com\n" +
                "Homepage:\n" +
                "test.com";

        System.out.println(cleaned(test));
    }
    public static String cleaned(String string){
        return string.replaceAll("\\s","")
                .replaceAll("[-()]","")
                .replaceAll("\n","")
                .replaceAll("H:","")
                .replaceAll("M:","")
                .replaceAll("W:","")
                .replaceAll("Homepage:","");
    }
}
