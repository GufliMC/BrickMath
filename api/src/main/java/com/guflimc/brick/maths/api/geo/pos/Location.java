package com.guflimc.brick.maths.api.geo.pos;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public record Location(@Nullable UUID worldId, double x, double y, double z, float yaw,
                       float pitch) implements Position {

    public final static Location ZERO = new Location(null, 0, 0, 0, 0, 0);

    public Location(@NotNull UUID worldId, Point point) {
        this(worldId, point.x(), point.y(), point.z(), 0, 0);
    }

    public Location(@NotNull Position pos) {
        this(null, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    public Location(@NotNull Point point) {
        this(null, point.x(), point.y(), point.z(), 0, 0);
    }

    public Location withPosition(@NotNull Position position) {
        return new Location(worldId, position.x(), position.y(), position.z(), position.yaw(), position.pitch());
    }

    public Location withWorldId(UUID worldId) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Location withYaw(float yaw) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Location withPitch(float pitch) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Position withYawPitch(float yaw, float pitch) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Position withPoint(@NotNull Point point) {
        return new Location(worldId, point.x(), point.y(), point.z(), yaw, pitch);
    }

    @Override
    public Location withX(double x) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Location withY(double y) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Location withZ(double z) {
        return new Location(worldId, x, y, z, yaw, pitch);
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
    public Location scale(double n) {
        return new Location(worldId, x() * n, y() * n, z() * n, yaw, pitch);
    }

    @Override
    public Location divide(double n) {
        return new Location(worldId, x / n, y / n, z / n, yaw, pitch);
    }

    @Override
    public Location add(double x, double y, double z) {
        return new Location(worldId, x() + x, y() + y, z() + z, yaw, pitch);
    }

    @Override
    public Location add(@NotNull Point other) {
        return new Location(worldId, x() + other.x(), y() + other.y(), z() + other.z(), yaw, pitch);
    }

    @Override
    public Location add(double n) {
        return new Location(worldId, x() + n, y() + n, z() + n, yaw, pitch);
    }

    @Override
    public Location floor() {
        return new Location(worldId, Math.floor(x()), Math.floor(y()), Math.floor(z()), yaw, pitch);
    }

    @Override
    public Location ceil() {
        return new Location(worldId, Math.ceil(x()), Math.ceil(y()), Math.ceil(z()), yaw, pitch);
    }

    @Override
    public Location subtract(Point other) {
        return add(new Vector(-other.x(), -other.y(), -other.z()));
    }

    @Override
    public Point subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    @Override
    public Point subtract(double n) {
        return add(-n);
    }

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof Location other)) {
            return false;
        }
        double epsilon = 0.0001d;
        return worldId.equals(other.worldId)
                && Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon
                && Math.abs(z - other.z) < epsilon
                && Math.abs(yaw - other.yaw) < epsilon
                && Math.abs(pitch - other.pitch) < epsilon;
    }

    @Override
    public String toString() {
        return "Location{world=" + worldId + ", x=" + x + ", y=" + y + ", z=" + z + ", yaw=" + yaw + ", pitch=" + pitch + "}";
    }

}
