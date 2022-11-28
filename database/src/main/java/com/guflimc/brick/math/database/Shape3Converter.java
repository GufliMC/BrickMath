package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.shape3d.Shape3;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class Shape3Converter implements AttributeConverter<Shape3, String>, javax.persistence.AttributeConverter<Shape3, String> {

    @Override
    public String convertToDatabaseColumn(Shape3 attribute) {
        return Shape3Serializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Shape3 convertToEntityAttribute(String dbData) {
        return Shape3Serializer.INSTANCE.deserialize(dbData);
    }

}