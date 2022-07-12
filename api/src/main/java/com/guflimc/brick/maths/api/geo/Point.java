package com.guflimc.brick.maths.api.geo;

public interface Point {

    double x();
    double y();
    double z();

    default int blockX() {
        return (int) x();
    }

    default int blockY() {
        return (int) y();
    }

    default int blockZ() {
        return (int) z();
    }
}
