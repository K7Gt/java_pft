package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Golem on 24.03.2017.
 */
public class EquationTests {

    @Test
    public  void test0(){
        Equation equation1 = new Equation(1,1,1);
        Assert.assertEquals(equation1.rootNumber(),0);
    }

    @Test
    public  void test1(){
        Equation equation1 = new Equation(1,2,1);
        Assert.assertEquals(equation1.rootNumber(),1);
    }

     @Test
    public  void test2(){
        Equation equation1 = new Equation(1,5,6);
        Assert.assertEquals(equation1.rootNumber(),2);
    }

    @Test
    public  void testLinear(){
        Equation equation1 = new Equation(0,1,1);
        Assert.assertEquals(equation1.rootNumber(),1);
    }

    @Test
    public  void testConstant(){
        Equation equation1 = new Equation(0,0,1);
        Assert.assertEquals(equation1.rootNumber(),0);
    }

    @Test
    public  void testZero(){
        Equation equation1 = new Equation(0,0,0);
        Assert.assertEquals(equation1.rootNumber(),-1);
    }


}
