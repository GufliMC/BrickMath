package com.guflimc.brick.math.common.geometry.shape3d;

import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import com.guflimc.brick.math.common.geometry.shape2d.Shape2;

import java.util.List;


public interface Shape3 {

    Shape2 contour();

    boolean contains(Point3 point);

    boolean intersects(Shape3 other);

}
