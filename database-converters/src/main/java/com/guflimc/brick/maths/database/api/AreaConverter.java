package com.guflimc.brick.maths.database.api;

import com.guflimc.brick.maths.api.geo.area.Area;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class AreaConverter implements AttributeConverter<Area, String>, javax.persistence.AttributeConverter<Area, String> {

    @Override
    public String convertToDatabaseColumn(Area attribute) {
        return AreaSerializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Area convertToEntityAttribute(String dbData) {
        return AreaSerializer.INSTANCE.deserialize(dbData);
    }

}