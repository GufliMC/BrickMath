package com.guflimc.brick.math.common.geometry.pos3;

public record Vector3(double x, double y, double z) implements IPoint3<Vector3> {

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
