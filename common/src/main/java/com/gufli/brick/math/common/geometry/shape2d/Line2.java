package com.gufli.brick.math.common.geometry.shape2d;

import com.gufli.brick.math.common.geometry.pos2.Point2;
import com.gufli.brick.math.common.geometry.pos2.Vector2;

public record Line2(Point2 from, Point2 to) {

    public Vector2 vector() {
        return new Vector2(to.x() - from.x(), to.y() - from.y());
    }

    @Override
    public String toString() {
        return "Line2{" + from + " -> " + to + "}";
    }
}

