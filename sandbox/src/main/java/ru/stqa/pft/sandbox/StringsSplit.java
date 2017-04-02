package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Golem on 31.03.2017.
 */
public class StringsSplit {

    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        String testString = "1\n2\n3";
        System.out.println(testString);

        for (String elem:testString.split("\n")){
            list.add(elem);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Element №" + (i+1) + "=" + list.get(i));
        }
        String[] mas = new String[list.size()];
        list.toArray(mas);
        System.out.println(mas[0]);
    }
}
