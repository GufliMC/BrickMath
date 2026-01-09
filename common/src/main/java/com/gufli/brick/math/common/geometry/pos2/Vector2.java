package com.gufli.brick.math.common.geometry.pos2;

public record Vector2(double x, double y) implements Point2 {

    public static final Vector2 ZERO = new Vector2(0, 0);

    //

    public Vector2(Point2 point) {
        this(point.x(), point.y());
    }

    //

    @Override
    public Vector2 with(double x, double y) {
        return new Vector2(x, y);
    }

    @Override
    public Vector2 withX(double x) {
        return with(x, y);
    }

    @Override
    public Vector2 withY(double y) {
        return with(x, y);
    }

    //

    public Vector2 addX(double x) {
        return withX(this.x + x);
    }

    public Vector2 addY(double y) {
        return withY(this.y + y);
    }

    public Vector2 scale(double v) {
        return new Vector2(x * v, y * v);
    }

    public Vector2 add(double x, double y) {
        return with(this.x + x, this.y + y);
    }

    public Vector2 add(Vector2 other) {
        return with(this.x + other.x(), this.y + other.y());
    }

    public Vector2 subtract(Vector2 other) {
        return with(this.x - other.x(), this.y - other.y());
    }

    public double length() {
        return distance(ZERO);
    }

    public Vector2 normalize() {
        double length = length();
        return with(x / length, y / length);
    }

    //

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof Vector2 other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon;
    }

    @Override
    public String toString() {
        return String.format("Vector2{x=%2.3f, y=%2.3f}", x, y);
    }
}
