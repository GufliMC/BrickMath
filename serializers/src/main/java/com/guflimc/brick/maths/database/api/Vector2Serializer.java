package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import com.guflimc.brick.maths.api.geo.pos.Vector2;
import com.guflimc.brick.maths.database.api.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class Vector2Serializer implements Serializer<Vector2> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .create();

    public static final Vector2Serializer INSTANCE = new Vector2Serializer();

    @Override
    public String serialize(Vector2 attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Vector2 deserialize(String dbData) {
        return gson.fromJson(dbData, Vector2.class);
    }
}