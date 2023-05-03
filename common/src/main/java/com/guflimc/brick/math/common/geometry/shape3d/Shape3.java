package com.guflimc.brick.math.common.geometry.shape3d;

import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import com.guflimc.brick.math.common.geometry.shape2d.Rectangle;
import com.guflimc.brick.math.common.geometry.shape2d.Shape2;

import java.util.List;


public interface Shape3 extends Iterable<Point3> {

    Shape2 contour();

    RectPrism bounds();

    boolean contains(Point3 point);

    boolean intersects(Shape3 other);

}
