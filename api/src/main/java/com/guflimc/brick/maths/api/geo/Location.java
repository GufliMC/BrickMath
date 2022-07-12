package com.guflimc.brick.maths.api.geo;

public record Location(String worldName, double x, double y, double z, float yaw,
                       float pitch) implements Position {

    @Override
    public Location withYaw(float yaw) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Location withPitch(float pitch) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withYawPitch(float yaw, float pitch) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Position withPoint(Point point) {
        return new Location(worldName, point.x(), point.y(), point.z(), yaw, pitch);
    }

    public Location withWorldName(String worldName) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Location withX(double x) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Location withY(double y) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Location withZ(double z) {
        return new Location(worldName, x, y, z, yaw, pitch);
    }

    @Override
    public Location addX(double x) {
        return withX(x() + x);
    }

    @Override
    public Location addY(double y) {
        return withY(y() + y);
    }

    @Override
    public Location addZ(double z) {
        return withZ(z() + z);
    }

    @Override
    public Location scale(double v) {
        return new Location(worldName, x() * v, y() * v, z() * v, yaw, pitch);
    }

    @Override
    public Point add(double x, double y, double z) {
        return new Location(worldName, x() + x, y() + y, z() + z, yaw, pitch);
    }

    @Override
    public Point add(Point other) {
        return new Location(worldName, x() + other.x(), y() + other.y(), z() + other.z(), yaw, pitch);
    }
}
