package com.guflimc.brick.math.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.math.common.geometry.shape2d.Polygon;
import com.guflimc.brick.math.common.geometry.shape2d.Rectangle;
import com.guflimc.brick.math.common.geometry.shape2d.Shape2;
import com.guflimc.brick.math.common.geometry.shape3d.PolyPrism;
import com.guflimc.brick.math.common.geometry.shape3d.RectPrism;
import com.guflimc.brick.math.common.geometry.shape3d.Shape3;
import com.guflimc.brick.math.database.util.RuntimeTypeAdapterFactory;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class Shape3Serializer implements Serializer<Shape3> {

    static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Shape3.class)
                    .registerSubtype(RectPrism.class, "rectprism")
                    .registerSubtype(PolyPrism.class, "polyprism"))
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Shape2.class)
                    .registerSubtype(Rectangle.class, "rectangle")
                    .registerSubtype(Polygon.class, "polygon"))
            .create();

    public final static Shape3Serializer INSTANCE = new Shape3Serializer();

    @Override
    public String serialize(Shape3 attribute) {
        return gson.toJson(attribute, Shape3.class);
    }

    @Override
    public Shape3 deserialize(String dbData) {
        return gson.fromJson(dbData, Shape3.class);
    }

}