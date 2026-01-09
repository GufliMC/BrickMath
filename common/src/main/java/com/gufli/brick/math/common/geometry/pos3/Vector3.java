package com.gufli.brick.math.common.geometry.pos3;

public record Vector3(double x, double y, double z) implements Point3 {

    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 IDENTITY = new Vector3(1, 1, 1);

    public Vector3(Point3 point) {
        this(point.x(), point.y(), point.z());
    }

    public static Vector3 between(Point3 a, Point3 b) {
        return new Vector3(b.x() - a.x(), b.y() - a.y(), b.z() - a.z());
    }

    //

    @Override
    public Vector3 with(double x, double y, double z) {
        return new Vector3(x, y, z);
    }

    //

    @Override
    public Vector3 withX(double x) {
        return (Vector3) Point3.super.withX(x);
    }

    @Override
    public Vector3 withY(double y) {
        return (Vector3) Point3.super.withY(y);
    }

    @Override
    public Vector3 withZ(double z) {
        return (Vector3) Point3.super.withZ(z);
    }

    @Override
    public Vector3 addX(double x) {
        return (Vector3) Point3.super.addX(x);
    }

    @Override
    public Vector3 addY(double y) {
        return (Vector3) Point3.super.addY(y);
    }

    @Override
    public Vector3 addZ(double z) {
        return (Vector3) Point3.super.addZ(z);
    }

    @Override
    public Vector3 scale(double n) {
        return (Vector3) Point3.super.scale(n);
    }

    @Override
    public Vector3 divide(double n) {
        return (Vector3) Point3.super.divide(n);
    }

    @Override
    public Vector3 add(double x, double y, double z) {
        return (Vector3) Point3.super.add(x, y, z);
    }

    @Override
    public Vector3 add(Point3 other) {
        return (Vector3) Point3.super.add(other);
    }

    @Override
    public Vector3 add(double n) {
        return (Vector3) Point3.super.add(n);
    }

    @Override
    public Vector3 floor() {
        return (Vector3)  Point3.super.floor();
    }

    @Override
    public Vector3 ceil() {
        return (Vector3) Point3.super.ceil();
    }

    @Override
    public Vector3 subtract(Point3 other) {
        return (Vector3) Point3.super.subtract(other);
    }

    @Override
    public Vector3 subtract(double x, double y, double z) {
        return (Vector3) Point3.super.subtract(x, y, z);
    }

    @Override
    public Vector3 subtract(double n) {
        return (Vector3)  Point3.super.subtract(n);
    }

    //

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3 other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon
                && Math.abs(z - other.z) < epsilon;
    }

    @Override
    public String toString() {
        return "Vector{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}
