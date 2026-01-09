package com.gufli.brick.math.common.geometry.pos2;

public interface Point2 {

    double x();

    double y();

    Point2 with(double x, double y);

    default Point2 withX(double x) {
        return with(x, y());
    }

    default Point2 withY(double y) {
        return with(x(), y);
    }

    default int blockX() {
        return (int) x();
    }

    default int blockY() {
        return (int) y();
    }

    default Point2 addX(double x) {
        return withX(x() + x);
    }

    default Point2 addY(double y) {
        return withY(y() + y);
    }

    default Point2 scale(double v) {
        return with(x() * v, y() * v);
    }

    default Point2 add(double x, double y) {
        return with(x() + x, y() + y);
    }

    default Point2 add(Point2 other) {
        return with(x() + other.x(), y() + other.y());
    }

    default Point2 subtract(Point2 other) {
        return with(x() - other.x(), y() - other.y());
    }

    default double distance(Point2 other) {
        return Math.sqrt(distanceSquared(other));
    }

    default double distanceSquared(Point2 other) {
        double diffX = x() - other.x();
        double diffY = y() - other.y();
        return diffX * diffX + diffY * diffY;
    }

    default Vector2 toVector() {
        return this instanceof Vector2 ? (Vector2) this : new Vector2(this);
    }
}
