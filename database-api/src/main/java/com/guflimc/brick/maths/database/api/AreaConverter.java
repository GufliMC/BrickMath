package com.guflimc.brick.maths.database.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guflimc.brick.maths.api.geo.area.Area;
import com.guflimc.brick.maths.api.geo.area.CuboidArea;
import com.guflimc.brick.maths.api.geo.area.PolyArea;
import com.guflimc.brick.maths.database.api.util.RuntimeTypeAdapterFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

@Converter
public class AreaConverter implements AttributeConverter<Area, String> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Area.class)
                    .registerSubtype(CuboidArea.class, "cuboid")
                    .registerSubtype(PolyArea.class, "polygon"))
            .create();

    @Override
    public String convertToDatabaseColumn(Area attribute) {
        return gson.toJson(attribute, Area.class);
    }

    @Override
    public Area convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, Area.class);
    }

}