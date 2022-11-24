package com.guflimc.brick.maths.api.geo.area;


import com.guflimc.brick.maths.api.geo.pos.Point;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import com.guflimc.brick.maths.api.geo.pos.Vector2;

import java.awt.*;
import java.util.List;

public record CuboidArea(Vector min, Vector max) implements Area {

    public CuboidArea(Vector min, Vector max) {
        Vector v1 = min.floor();
        Vector v2 = max.floor();

        double minX = Math.min(v1.x(), v2.x());
        double minY = Math.min(v1.y(), v2.y());
        double minZ = Math.min(v1.z(), v2.z());

        double maxX = Math.max(v1.x(), v2.x());
        double maxY = Math.max(v1.y(), v2.y());
        double maxZ = Math.max(v1.z(), v2.z());

        this.min = new Vector(minX, minY, minZ);
        this.max = new Vector(maxX, maxY, maxZ);
    }

    public static CuboidArea of(Point v1, Point v2) {
        return new CuboidArea(Vector.of(v1), Vector.of(v2));
    }

    public static CuboidArea of(double x1, double y1, double z1, double x2, double y2, double z2) {
        return CuboidArea.of(new Vector(x1, y1, z1), new Vector(x2, y2, z2));
    }

    public static CuboidArea of(Point point) {
        return CuboidArea.of(new Vector(0, 0, 0), point);
    }

    @Override
    public boolean contains(Point point) {
        point = point.floor();
        return min.x() <= point.x() && max.x() >= point.x() &&
                min.y() <= point.y() && max.y() >= point.y() &&
                min.z() <= point.z() && max.z() >= point.z();
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
