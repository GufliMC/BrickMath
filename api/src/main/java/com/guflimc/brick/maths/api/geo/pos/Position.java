package com.guflimc.brick.maths.api.geo.pos;

public sealed interface Position extends Point permits Location {

    float yaw();

    float pitch();

    Position withYaw(float yaw);

    Position withPitch(float pitch);

    Position withYawPitch(float yaw, float pitch);

    Position withPoint(Point point);

}
