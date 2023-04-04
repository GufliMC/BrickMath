package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;

public class Point3Serializer implements Serializer<Point3> {

    public static final Point3Serializer INSTANCE = new Point3Serializer();

    @Override
    public String serialize(Point3 attribute) {
        return GsonTools.serialize(attribute);
    }

    @Override
    public Point3 deserialize(String dbData) {
        return GsonTools.deserialize(dbData, Point3.class, Vector3.class, Location.class);
    }
}