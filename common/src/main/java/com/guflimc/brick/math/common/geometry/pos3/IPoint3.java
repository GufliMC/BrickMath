package com.guflimc.brick.math.common.geometry.pos3;

public sealed interface IPoint3<T extends IPoint3<T>> extends Point3 permits Vector3, Position {

    T with(double x, double y, double z);

    //

    @Override
    default T withX(double x) {
        return (T) Point3.super.withX(x);
    }

    @Override
    default T withY(double y) {
        return (T) Point3.super.withY(y);
    }

    @Override
    default T withZ(double z) {
        return (T) Point3.super.withZ(z);
    }

    @Override
    default T addX(double x) {
        return (T) Point3.super.addX(x);
    }

    @Override
    default T addY(double y) {
        return (T) Point3.super.addY(y);
    }

    @Override
    default T addZ(double z) {
        return (T) Point3.super.addZ(z);
    }

    @Override
    default T scale(double n) {
        return (T) Point3.super.scale(n);
    }

    @Override
    default T divide(double n) {
        return (T) Point3.super.divide(n);
    }

    @Override
    default T add(double x, double y, double z) {
        return (T) Point3.super.add(x, y, z);
    }

    @Override
    default T add(Point3 other) {
        return (T) Point3.super.add(other);
    }

    @Override
    default T add(double n) {
        return (T) Point3.super.add(n);
    }

    @Override
    default T floor() {
        return (T) Point3.super.floor();
    }

    @Override
    default T ceil() {
        return (T) Point3.super.ceil();
    }

    @Override
    default T subtract(Point3 other) {
        return (T) Point3.super.subtract(other);
    }

    @Override
    default T subtract(double x, double y, double z) {
        return (T) Point3.super.subtract(x, y, z);
    }

    @Override
    default T subtract(double n) {
        return (T) Point3.super.subtract(n);
    }
}
