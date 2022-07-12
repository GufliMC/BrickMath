package com.guflimc.brick.maths.api.geo;

public record Vector(double x, double y, double z) implements Point {

    @Override
    public Vector addX(double x) {
        return withX(this.x + x);
    }

    @Override
    public Vector addY(double y) {
        return withY(this.y + y);
    }

    @Override
    public Vector addZ(double z) {
        return withZ(this.z + z);
    }

    @Override
    public Vector withX(double x) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector withY(double y) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector withZ(double z) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector scale(double v) {
        return new Vector(x * v, y * v, z * v);
    }

    @Override
    public Vector add(double x, double y, double z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vector add(Point other) {
        return new Vector(this.x + other.x(), this.y + other.y(), this.z + other.z());
    }
}
