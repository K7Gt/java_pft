package ru.stqa.pft.sandbox;

/**
 * Created by Golem on 11.03.2017.
 */
public class Point {
    double oX;
    double oY;

    public Point(double oX , double oY){
        this.oX = oX;
        this.oY = oY;
    }

    //Method of class Point for case 2

    public double distance(Point p){
        double result;
        result = Math.sqrt(Math.pow((this.oX - p.oX),2) + Math.pow((this.oY - p.oY),2));
        return result;
    }

}
