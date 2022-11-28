package com.guflimc.brick.math.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import com.guflimc.brick.math.database.util.RuntimeTypeAdapterFactory;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class Point3Serializer implements Serializer<Point3> {

    static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.builder().allowMissingComponentValues().create())
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Point3.class)
                    .registerSubtype(Location.class, "location")
                    .registerSubtype(Vector3.class, "vector3"))
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Point2.class)
                    .registerSubtype(Vector2.class, "vector2"))
            .create();

    public static final Point3Serializer INSTANCE = new Point3Serializer();

    @Override
    public String serialize(Point3 attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Point3 deserialize(String dbData) {
        return gson.fromJson(dbData, Point3.class);
    }
}