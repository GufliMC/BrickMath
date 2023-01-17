package com.guflimc.brick.math.common.geometry.shape2d;

import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record Rectangle(Vector2 min, Vector2 max) implements Shape2 {

    @Override
    public List<Vector2> vertices() {
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

    public Vector2 dimensions() {
        return max.subtract(min);
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
