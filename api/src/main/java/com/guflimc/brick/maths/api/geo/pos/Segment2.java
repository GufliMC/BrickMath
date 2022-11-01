package com.guflimc.brick.maths.api.geo.pos;

import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Segment2(Vector2 point1, Vector2 point2) implements ISegment {

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
