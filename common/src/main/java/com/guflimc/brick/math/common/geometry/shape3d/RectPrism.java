package com.guflimc.brick.math.common.geometry.shape3d;


import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import com.guflimc.brick.math.common.geometry.shape2d.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;
import java.util.Iterator;
import java.util.Vector;

public record RectPrism(double minY, double maxY, Rectangle rectangle) implements ShapePrism3 {

    public static RectPrism of(Point3 min, Point3 max) {
        Point3 v1 = min.floor();
        Point3 v2 = max.floor();

        double minX = Math.min(v1.x(), v2.x());
        double minY = Math.min(v1.y(), v2.y());
        double minZ = Math.min(v1.z(), v2.z());

        double maxX = Math.max(v1.x(), v2.x());
        double maxY = Math.max(v1.y(), v2.y());
        double maxZ = Math.max(v1.z(), v2.z());

        Rectangle rectangle = new Rectangle(new Vector2(minX, minZ), new Vector2(maxX, maxZ));
        return new RectPrism(minY, maxY, rectangle);
    }

    public static RectPrism of(double x1, double y1, double z1, double x2, double y2, double z2) {
        return RectPrism.of(new Vector3(x1, y1, z1), new Vector3(x2, y2, z2));
    }

    public static RectPrism of(Point3 point) {
        return RectPrism.of(new Vector3(0, 0, 0), point);
    }

    //

    @Override
    public boolean contains(Point3 point) {
        point = point.floor();
        return rectangle.min().x() <= point.x() && rectangle.max().x() >= point.x() &&
                minY <= point.y() && maxY >= point.y() &&
                rectangle.min().y() <= point.z() && rectangle.max().y() >= point.z();
    }

    @Override
    public Rectangle contour() {
        return rectangle;
    }

    @Override
    public RectPrism bounds() {
        return this;
    }

    @Override
    public double minY() {
        return minY;
    }

    @Override
    public double maxY() {
        return maxY;
    }

    public Vector3 min() {
        return new Vector3(rectangle.min().x(), minY, rectangle.min().y());
    }

    public Vector3 max() {
        return new Vector3(rectangle.max().x(), maxY, rectangle.max().y());
    }

    public Vector3 dimensions() {
        Vector2 dim2d = rectangle.dimensions();
        return new Vector3(dim2d.x(), maxY - minY, dim2d.y());
    }

    @NotNull
    @Override
    public Iterator<Point3> iterator() {
        Vector3 dimensions = dimensions();
        int max = dimensions.blockX() * dimensions.blockY() * dimensions.blockZ();
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < max;
            }

            @Override
            public Point3 next() {
                int x = index % dimensions.blockX();
                int y = (index / dimensions.blockX()) % dimensions.blockY();
                int z = index / (dimensions.blockX() * dimensions.blockY());
                index++;
                return new Vector3(rectangle.min().x() + x, minY + y, rectangle.min().y() + z);
            }
        };
    }
}
