package com.guflimc.brick.math.common.geometry.shape2d;

import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;

import java.util.Iterator;
import java.util.List;


public interface Shape2 extends Iterable<Point2> {

    List<Vector2> vertices();

    boolean contains(Point2 point);

    default double area() {
        throw new UnsupportedOperationException("Not implemented.");
    }

}
