package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.pos.Location;
import com.guflimc.brick.maths.database.api.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class LocationSerializer implements Serializer<Location> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.builder().allowMissingComponentValues().create())
            .create();

    public static final LocationSerializer INSTANCE = new LocationSerializer();

    @Override
    public String serialize(Location attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Location deserialize(String dbData) {
        return gson.fromJson(dbData, Location.class);
    }
}