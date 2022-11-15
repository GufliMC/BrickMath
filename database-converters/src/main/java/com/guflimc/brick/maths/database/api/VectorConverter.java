package com.guflimc.brick.maths.database.api;

import com.guflimc.brick.maths.api.geo.pos.Vector;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class VectorConverter implements AttributeConverter<Vector, String>, javax.persistence.AttributeConverter<Vector, String> {

    @Override
    public String convertToDatabaseColumn(Vector attribute) {
        return VectorSerializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Vector convertToEntityAttribute(String dbData) {
        return VectorSerializer.INSTANCE.deserialize(dbData);
    }
}