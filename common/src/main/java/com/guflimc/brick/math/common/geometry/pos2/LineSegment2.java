package com.guflimc.brick.math.common.geometry.pos2;

import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record LineSegment2(Vector2 point1, Vector2 point2) implements ISegment {

    @Override
    public @NotNull IPoint p1() {
        return point1;
    }

    @Override
    public @NotNull IPoint p2() {
        return point2;
    }

    @Override
    public @Nullable String name() {
        return null;
    }
}
