package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.sandbox.Point;

public class PointTest {

    Point point = new Point(1,1);

    Point p2 = new Point(2, 2);
    Point p3 = new Point(0, 0);
    Point p4 = new Point(3, 0);

    @Test
    public void testPoint1(){
        Assert.assertEquals(point.distance(p2),1.4142135623730951);
    }

    @Test
    public void testPoint2(){
        Assert.assertEquals(point.distance(p3),1.4142135623730951);
    }

    @Test
    public void testPoint3(){
        Assert.assertEquals(point.distance(p3),1.4142135623730951);
    } 


}
