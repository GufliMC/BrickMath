package com.guflimc.brick.maths.api.geo;

public interface Position extends Point {

    float yaw();

    float pitch();

    Position withYaw(float yaw);

    Position withPitch(float pitch);

    Position withYawPitch(float yaw, float pitch);

    Position withPoint(Point point);

}
