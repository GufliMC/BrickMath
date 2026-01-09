package com.gufli.brick.math.common.geometry.shape3d;

public interface ShapePrism3 extends Shape3 {

    double minY();

    double maxY();

    default boolean intersects(Shape3 other) {
        if (!(other instanceof ShapePrism3 ps)) {
            throw new UnsupportedOperationException("Not implemented.");
        }

        if (minY() < ps.maxY() && maxY() > ps.minY()) {
            return contour().intersects(other.contour());
        }
        return false;
    }

}
