package com.guflimc.brick.maths.api.geo;

public interface Direction {

    float yaw();

    float pitch();

    Direction withPitch(float pitch);

    Direction withYaw(float yaw);

}
