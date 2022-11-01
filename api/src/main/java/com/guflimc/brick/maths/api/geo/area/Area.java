package com.guflimc.brick.maths.api.geo.area;

import com.guflimc.brick.maths.api.geo.pos.Point;
import com.guflimc.brick.maths.api.geo.pos.Vector2;

import java.awt.*;
import java.util.List;


public interface Area {

    boolean contains(Point point);

    Contour contour();

    double minY();

    double maxY();

    default java.awt.geom.Area geometry() {
        List<Vector2> vertices = contour().vertices();
        int[] xpoints = new int[vertices.size()];
        int[] ypoints = new int[vertices.size()];
        for ( int i = 0 ; i < vertices.size(); i++ ) {
            xpoints[i] = (int) vertices.get(i).x();
            ypoints[i] = (int) vertices.get(i).y();
        }
        return new java.awt.geom.Area(new Polygon(xpoints, ypoints, vertices.size()));
    }

    /**
     * Not sure if this is the most efficient way. Time will tell.
     */
    default boolean intersects(Area other) {
        if ( minY() < other.maxY() && maxY() > other.minY() ) {
            java.awt.geom.Area tmp = geometry();
            tmp.intersect(other.geometry());
            return tmp.isEmpty();
        }
        return false;
    }

}
