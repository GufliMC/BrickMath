package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;

public class Point2Serializer implements Serializer<Point2> {

    public static final Point2Serializer INSTANCE = new Point2Serializer();

    @Override
    public String serialize(Point2 attribute) {
        return Point3Serializer.gson.toJson(attribute);
    }

    @Override
    public Point2 deserialize(String dbData) {
        return Point3Serializer.gson.fromJson(dbData, Vector2.class);
    }
}