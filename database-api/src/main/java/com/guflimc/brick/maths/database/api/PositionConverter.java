package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

@Converter
public class PositionConverter implements AttributeConverter<Position, String> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .create();

    @Override
    public String convertToDatabaseColumn(Position attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Position convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, Position.class);
    }
}