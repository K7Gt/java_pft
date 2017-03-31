package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Golem on 31.03.2017.
 */
public class Collections {

    public  static void main (String[] args){
        String[] langs = {"Java","C#","Python","PHP"};
        /*
            langs[0] = "Java";
            langs[1] = "C#";
            langs[2] = "Python";
            langs[3] = "PHP";
        */

        List<String> languages = Arrays.asList("Java","C#","Python","PHP");
        /*
            languages.add("Java");
            languages.add("C#");
            languages.add("Python");
            languages.add("PHP");
        */
        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
        System.out.println("Fori loops");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Я хочу выучить " + languages.get(i));
        }

    }
}
