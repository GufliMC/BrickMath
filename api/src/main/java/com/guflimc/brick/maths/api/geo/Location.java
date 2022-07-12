package com.guflimc.brick.maths.api.geo;

public interface Location extends Point {

    String worldName();

    Location withWorldName(String worldName);

}
