package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.Location;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

@Converter
public class LocationConverter implements AttributeConverter<Location, String> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.builder().allowMissingComponentValues().create())
            .create();

    @Override
    public String convertToDatabaseColumn(Location attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Location convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, Location.class);
    }
}