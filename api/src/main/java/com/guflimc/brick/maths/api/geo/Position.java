package com.guflimc.brick.maths.api.geo;

public record Position(String worldName, double x, double y, double z, float yaw, float pitch) implements Location, Direction {

}
