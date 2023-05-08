package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.shape3d.PolyPrism;
import com.guflimc.brick.math.common.geometry.shape3d.RectPrism;
import com.guflimc.brick.math.common.geometry.shape3d.Shape3;

public class Shape3Serializer implements Serializer<Shape3> {

    public final static Shape3Serializer INSTANCE = new Shape3Serializer();

    @Override
    public String serialize(Shape3 attribute) {
        return GsonTools.serialize(attribute);
    }

    @Override
    public Shape3 deserialize(String dbData) {
        return GsonTools.deserialize(dbData, Shape3.class);
    }

}