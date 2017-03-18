package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Golem on 18.03.2017.
 */
public class PointTests {

    @Test
    public void test1(){

        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }
    @Test
     public void test2(){
        Point p1 = new Point(0,1);
        Point p2 = new Point(0,4);
       Assert.assertEquals(p1.distance(p2),3.0);
    }
    @Test
     public void test3(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(-1,-1);
       Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }
    @Test
     public void test4(){
        Point p1 = new Point(-1,1);
        Point p2 = new Point(1,-1);
       Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }
    @Test
     public void test5(){
        Point p1 = new Point(-7,-7);
        Point p2 = new Point(0,0);
       Assert.assertEquals(p1.distance(p2),9.899494936611665);
    }



}
