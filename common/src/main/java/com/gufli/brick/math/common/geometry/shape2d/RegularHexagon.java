package com.gufli.brick.math.common.geometry.shape2d;

import com.gufli.brick.math.common.geometry.pos2.Point2;
import com.gufli.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record RegularHexagon(Point2 center, int radius) implements Shape2 {

    private double width() {
        return 1.73205080757 * radius;
    }

    @Override
    public List<Point2> vertices() {
        double w = width();
        return List.of(
                new Vector2(center.x(), center.y() + radius),
                new Vector2(center.x() + (w / 2d), center.y() + (radius / 2d)),
                new Vector2(center.x() + (w / 2d), center.y() - (radius / 2d)),
                new Vector2(center.x(), center.y() - radius),
                new Vector2(center.x() - (w / 2d), center.y() - (radius / 2d)),
                new Vector2(center.x() - (w / 2d), center.y() + (radius / 2d))
        );
    }

    @Override
    public boolean contains(Point2 point) {
        double qx = Math.abs(point.x() - center.x());
        double qy = Math.abs(point.y() - center.y());
        if (qx > radius || qy > radius) return false; // outside circle
        if (qx < radius && qy < radius / 2d) return true; // inside rectangular part
        double v = (radius / 2d) - 0.660254 * qx; // inside triangle part
        return qy <= v;
    }

    @Override
    public Rectangle bounds() {
        return new Rectangle(
                new Vector2(center.x() - radius, center.y() - radius),
                new Vector2(center.x() + radius, center.y() + radius)
        );
    }

    @NotNull
    @Override
    public Iterator<Point2> iterator() {
        throw new UnsupportedOperationException("Not implemented yet"); // TODO;
    }
}