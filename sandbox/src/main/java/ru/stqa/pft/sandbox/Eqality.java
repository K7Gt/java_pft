package ru.stqa.pft.sandbox;

/**
 * Created by Golem on 24.03.2017.
 */
public class Eqality {

    public static void main(String[] args){
        String s1 = "firefox";
        String s2 = new String(s1);
        String s3 = "fire" + "fox";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2)+"\n");
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));

    }
}
