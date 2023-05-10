package com.guflimc.brick.math.common.geometry.shape2d;

import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;
import java.util.Iterator;
import java.util.List;

public record Rectangle(Point2 min, Point2 max) implements Shape2 {

    public Rectangle(Point2 min, Point2 max) {
        this.min = new Vector2(Math.min(min.x(), max.x()), Math.min(min.y(), max.y()));
        this.max = new Vector2(Math.max(min.x(), max.x()), Math.max(min.y(), max.y()));
    }

    @Override
    public List<Point2> vertices() {
        return List.of(
                min,
                new Vector2(max.x(), min.y()),
                max,
                new Vector2(min.x(), max.y())
        );
    }

    @Override
    public boolean contains(Point2 point) {
        return point.x() >= min.x() && point.x() <= max.x() &&
                point.y() >= min.y() && point.y() <= max.y();
    }

    @Override
    public Rectangle bounds() {
        return this;
    }

    public Vector2 dimensions() {
        return new Vector2(max).subtract(new Vector2(min));
    }

    @Override
    public Area geometry() {
        return new Area(new java.awt.Rectangle(
                (int) min.x(), (int) min.y(),
                (int) (max.x() - min.x()), (int) (max.y() - min.y())
        ));
    }

    @NotNull
    @Override
    public Iterator<Point2> iterator() {
        Vector2 dimensions = dimensions();
        int max = dimensions.blockX() * dimensions.blockY();
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < max;
            }

            @Override
            public Point2 next() {
                int x = index % dimensions.blockX();
                int y = (index / dimensions.blockX()) % dimensions.blockY();
                index++;
                return new Vector2(min.x() + x, min.y() + y);
            }
        };
    }
}
