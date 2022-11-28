package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.shape2d.Shape2;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class Shape2Converter implements AttributeConverter<Shape2, String>, javax.persistence.AttributeConverter<Shape2, String> {

    @Override
    public String convertToDatabaseColumn(Shape2 attribute) {
        return Shape2Serializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Shape2 convertToEntityAttribute(String dbData) {
        return Shape2Serializer.INSTANCE.deserialize(dbData);
    }

}