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

    public static double distance(Point p1, Point p2){
        double result;
        result = Math.sqrt(Math.pow((p1.oX - p2.oX),2) + Math.pow((p1.oY - p2.oY),2));
        return result;
    }

    public double distance(Point p){
        double result;
        result = Math.sqrt(Math.pow((this.oX - p.oX),2) + Math.pow((this.oY - p.oY),2));
        return result;
    }

    public static void main(String[] args){
        Point p1 = new Point(1,2);
        Point p2 = new Point(-2,-10);
        double  distance;
        String result = "Дистанция между точками равна: = ";

        //Case 1
        distance = distance(p1,p2);
        System.out.println("Результат вызова статического метода:\n" + result + distance + ".\n");

        // Case 2
        distance = p1.distance(p2);
        System.out.println("Результат вызова метода класса:\n" + result + distance + ".");
    }
}
