package com.guflimc.brick.maths.database.api;

import com.guflimc.brick.maths.api.geo.pos.Vector2;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class Vector2Converter implements AttributeConverter<Vector2, String>, javax.persistence.AttributeConverter<Vector2, String> {

    @Override
    public String convertToDatabaseColumn(Vector2 attribute) {
        return Vector2Serializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Vector2 convertToEntityAttribute(String dbData) {
        return Vector2Serializer.INSTANCE.deserialize(dbData);
    }
}