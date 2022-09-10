package com.guflimc.brick.maths.api.geo.pos;

import java.util.Collection;

public record Vector2(double x, double y) {

    public static final Vector2 ZERO = new Vector2(0, 0);

    public Vector2 addX(double x) {
        return withX(this.x + x);
    }

    public Vector2 addY(double y) {
        return withY(this.y + y);
    }

    public Vector2 withX(double x) {
        return new Vector2(x, y);
    }

    public Vector2 withY(double y) {
        return new Vector2(x, y);
    }

    public Vector2 scale(double v) {
        return new Vector2(x * v, y * v);
    }

    public Vector2 add(double x, double y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x(), this.y + other.y());
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x(), this.y - other.y());
    }

    public Vector2 add(Collection<Vector2> others) {
        double x = this.x;
        double y = this.y;
        for ( Vector2 vec : others ) {
            x += vec.x;
            y += vec.y;
        }
        return new Vector2(x, y);
    }
}
