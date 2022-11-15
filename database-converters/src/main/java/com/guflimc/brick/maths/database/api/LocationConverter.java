package com.guflimc.brick.maths.database.api;

import com.guflimc.brick.maths.api.geo.pos.Location;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class LocationConverter implements AttributeConverter<Location, String>, javax.persistence.AttributeConverter<Location, String> {

    @Override
    public String convertToDatabaseColumn(Location attribute) {
        return LocationSerializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Location convertToEntityAttribute(String dbData) {
        return LocationSerializer.INSTANCE.deserialize(dbData);
    }
}