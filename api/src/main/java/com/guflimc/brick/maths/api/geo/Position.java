package com.guflimc.brick.maths.api.geo;

public record Position(String worldName, double x, double y, double z, float yaw, float pitch) implements Location, Direction {

    @Override
    public Position withPitch(float pitch) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withYaw(float yaw) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withWorldName(String worldName) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withX(double x) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withY(double y) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withZ(double z) {
        return new Position(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position addX(double x) {
        return withX(x() + x);
    }

    @Override
    public Position addY(double y) {
        return withY(y() + y);
    }

    @Override
    public Position addZ(double z) {
        return withZ(z() + z);
    }

    @Override
    public Position scale(double v) {
        return new Position(worldName, x() * v, y() * v, z() * v, yaw, pitch);
    }

    @Override
    public Point add(double x, double y, double z) {
        return new Position(worldName, x() + x, y() + y, z() + z, yaw, pitch);
    }

    @Override
    public Point add(Point other) {
        return new Position(worldName, x() + other.x(), y() + other.y(), z() + other.z(), yaw, pitch);
    }
}
