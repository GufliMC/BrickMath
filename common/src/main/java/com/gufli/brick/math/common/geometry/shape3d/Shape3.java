package com.gufli.brick.math.common.geometry.shape3d;

import com.gufli.brick.math.common.geometry.pos3.Point3;
import com.gufli.brick.math.common.geometry.shape2d.Shape2;


public interface Shape3 extends Iterable<Point3> {

    Shape2 contour();

    RectPrism bounds();

    boolean contains(Point3 point);

    boolean intersects(Shape3 other);

}
