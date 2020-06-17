package ru.stqa.ptf.sandbox;

public class JavaProg {

    public static void main(String[] args) {

        Point point = new Point(1, 1);

        Point p2 = new Point(2, 2);
        Point p3 = new Point(0, 0);
        Point p4 = new Point(3, 0);

        System.out.println(point.distance(p2));
        System.out.println(point.distance(p3));
        System.out.println(point.distance(p3));

    }


}