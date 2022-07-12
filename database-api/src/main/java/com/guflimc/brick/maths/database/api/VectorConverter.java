package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.Vector;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

@Converter
public class VectorConverter implements AttributeConverter<Vector, String> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .create();

    @Override
    public String convertToDatabaseColumn(Vector attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Vector convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, Vector.class);
    }
}