package com.gufli.brick.math.common.geometry.pos3;

public interface Point3 {

    double x();

    double y();

    double z();

    Point3 with(double x, double y, double z);

    //

    default Point3 withX(double x) {
        return with(x, y(), z());
    }

    default Point3 withY(double y) {
        return with(x(), y, z());
    }

    default Point3 withZ(double z) {
        return with(x(), y(), z);
    }

    default int blockX() {
        return (int) x();
    }

    default int blockY() {
        return (int) y();
    }

    default int blockZ() {
        return (int) z();
    }

    default double distance(Point3 other) {
        return Math.sqrt(distanceSquared(other));
    }

    default double distanceSquared(Point3 other) {
        double diffX = x() - other.x();
        double diffY = y() - other.y();
        double diffZ = z() - other.z();
        return diffX * diffX + diffY * diffY + diffZ * diffZ;
    }

    default Point3 addX(double x) {
        return withX(x() + x);
    }

    default Point3 addY(double y) {
        return withY(y() + y);
    }

    default Point3 addZ(double z) {
        return withZ(z() + z);
    }

    default Point3 scale(double n) {
        return with(x() * n, y() * n, z() * n);
    }

    default Point3 divide(double n) {
        if ( n == 0 ) {
            throw new ArithmeticException("Division by zero");
        }
        return with(x() / n, y() / n, z() / n);
    }

    default Point3 add(double x, double y, double z) {
        return with(x() + x, y() + y, z() + z);
    }

    default Point3 add(Point3 other) {
        return add(other.x(), other.y(), other.z());
    }

    default Point3 add(double n) {
        return add(n, n, n);
    }

    default Point3 floor() {
        return with(Math.floor(x()), Math.floor(y()), Math.floor(z()));
    }

    default Point3 ceil() {
        return with(Math.ceil(x()), Math.ceil(y()), Math.ceil(z()));
    }

    default Point3 subtract(Point3 other) {
        return add(-other.x(), -other.y(), -other.z());
    }

    default Point3 subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    default Point3 subtract(double n) {
        return add(-n);
    }

    default Vector3 toVector() {
        return this instanceof Vector3 ? (Vector3) this : new Vector3(this);
    }

}
