package ru.stqa.ptf.sandbox;

public class Point {

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {

    }

    int x;
    int y;


    public double distance(Point p2) {
        
        return Math.sqrt(Math.pow((p2.x - this.x), 2) + Math.pow((p2.y - this.y), 2));

    }

}
