package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import com.guflimc.brick.maths.database.api.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class VectorSerializer implements Serializer<Vector> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .create();

    public static final VectorSerializer INSTANCE = new VectorSerializer();

    @Override
    public String serialize(Vector attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Vector deserialize(String dbData) {
        return gson.fromJson(dbData, Vector.class);
    }
}