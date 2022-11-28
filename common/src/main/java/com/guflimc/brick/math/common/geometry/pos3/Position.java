package com.guflimc.brick.math.common.geometry.pos3;

import org.jetbrains.annotations.NotNull;

public sealed interface Position extends IPoint3<Position> permits Location {

    float yaw();

    float pitch();

    Position withYaw(float yaw);

    Position withPitch(float pitch);

    Position withYawPitch(float yaw, float pitch);

    Position with(@NotNull Point3 point);

    default Vector3 direction() {
        double y = -Math.sin(Math.toRadians(pitch()));
        double xz = Math.cos(Math.toRadians(pitch()));
        double x = -xz * Math.sin(Math.toRadians(yaw()));
        double z = xz * Math.cos(Math.toRadians(yaw()));
        return new Vector3(x, y, z);
    }

}
