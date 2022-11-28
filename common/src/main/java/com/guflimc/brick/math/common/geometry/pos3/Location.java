package com.guflimc.brick.math.common.geometry.pos3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public record Location(@Nullable UUID worldId,
                       double x, double y, double z,
                       float yaw, float pitch) implements Position {

    public final static Location ZERO = new Location(null, 0, 0, 0, 0, 0);

    //

    public Location(UUID worldId, @NotNull Point3 point) {
        this(worldId, point.x(), point.y(), point.z(), 0, 0);
    }

    public Location(@NotNull Position pos) {
        this(null, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    public Location(@NotNull Point3 point) {
        this(null, point);
    }

    //

    @Override
    public Location with(double x, double y, double z) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    //

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
    public Location withYawPitch(float yaw, float pitch) {
        return new Location(worldId, x, y, z, yaw, pitch);
    }

    @Override
    public Location with(@NotNull Point3 point) {
        return new Location(worldId, point.x(), point.y(), point.z(), yaw, pitch);
    }

    public Location with(@NotNull Position position) {
        return new Location(worldId, position.x(), position.y(), position.z(), position.yaw(), position.pitch());
    }

    //

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof Location other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Objects.equals(worldId, other.worldId)
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
