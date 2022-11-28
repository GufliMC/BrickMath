package com.guflimc.brick.math.common.geometry.shape3d;

import com.guflimc.brick.math.common.geometry.pos2.Vector2;

import java.awt.*;
import java.awt.geom.Area;
import java.util.List;

public interface ShapePrism3 extends Shape3 {

    double minY();

    double maxY();

    default Area geometry() {
        List<Vector2> vertices = contour().vertices();
        int[] xpoints = new int[vertices.size()];
        int[] ypoints = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            xpoints[i] = (int) vertices.get(i).x();
            ypoints[i] = (int) vertices.get(i).y();
        }
        return new Area(new Polygon(xpoints, ypoints, vertices.size()));
    }

    default boolean intersects(Shape3 other) {
        if (!(other instanceof ShapePrism3 ps)) {
            throw new UnsupportedOperationException("Not implemented.");
        }

        if (minY() < ps.maxY() && maxY() > ps.minY()) {
            Area tmp = geometry();
            tmp.intersect(ps.geometry());
            return !tmp.isEmpty(); // empty means no intersection
        }
        return false;
    }

}
