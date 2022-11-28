package com.guflimc.brick.math.common.geometry.shape2d;

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

    @NotNull
    @Override
    public Iterator<Vector2> iterator() {
        return vertices().iterator();
    }

    public Vector2 dimensions() {
        return max.subtract(min);
    }
}
