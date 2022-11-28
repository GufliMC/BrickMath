package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.pos3.Point3;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class Point3Converter implements AttributeConverter<Point3, String>, javax.persistence.AttributeConverter<Point3, String> {

    @Override
    public String convertToDatabaseColumn(Point3 attribute) {
        return Point3Serializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Point3 convertToEntityAttribute(String dbData) {
        return Point3Serializer.INSTANCE.deserialize(dbData);
    }
}