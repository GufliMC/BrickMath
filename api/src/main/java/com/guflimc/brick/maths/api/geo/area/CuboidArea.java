package com.guflimc.brick.maths.api.geo.area;


import com.guflimc.brick.maths.api.geo.pos.Point;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import com.guflimc.brick.maths.api.geo.pos.Vector2;

import java.awt.*;
import java.util.List;

public record CuboidArea(Vector min, Vector max) implements Area {

    public static CuboidArea of(Point iv1, Point iv2) {
        double minX = Math.min(iv1.x(), iv2.x());
        double minY = Math.min(iv1.y(), iv2.y());
        double minZ = Math.min(iv1.z(), iv2.z());

        double maxX = Math.max(iv1.x(), iv2.x());
        double maxY = Math.max(iv1.y(), iv2.y());
        double maxZ = Math.max(iv1.z(), iv2.z());

        return new CuboidArea(
                new Vector(minX, minY, minZ),
                new Vector(maxX, maxY, maxZ)
        );
    }

    @Override
    public boolean contains(Point point) {
        return min.x() <= Math.floor(point.x()) && max.x() >= Math.floor(point.x()) &&
                min.y() <= Math.floor(point.y()) && max.y() >= Math.floor(point.y()) &&
                min.z() <= Math.floor(point.z()) && max.z() >= Math.floor(point.z());
    }

    public Vector min() {
        return min;
    }

    public Vector max() {
        return max;
    }

    @Override
    public Contour contour() {
        return new Contour(List.of(
                new Vector2(min.x(), min.z()),
                new Vector2(max.x(), min.z()),
                new Vector2(max.x(), max.z()),
                new Vector2(min.x(), max.z())
        ));
    }

    @Override
    public double minY() {
        return min.y();
    }

    @Override
    public double maxY() {
        return max.y();
    }

    @Override
    public java.awt.geom.Area geometry() {
        return new java.awt.geom.Area(new Rectangle(
                (int) min.x(),
                (int) min.z(),
                (int) (max.x() - min.x()),
                (int) (max.z() - min.z())
        ));
    }
}
