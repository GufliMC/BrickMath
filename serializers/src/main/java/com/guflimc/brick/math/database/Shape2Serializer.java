package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.shape2d.Shape2;

public class Shape2Serializer implements Serializer<Shape2> {

    public final static Shape2Serializer INSTANCE = new Shape2Serializer();

    @Override
    public String serialize(Shape2 attribute) {
        return Shape3Serializer.gson.toJson(attribute, Shape2.class);
    }

    @Override
    public Shape2 deserialize(String dbData) {
        return Shape3Serializer.gson.fromJson(dbData, Shape2.class);
    }

}