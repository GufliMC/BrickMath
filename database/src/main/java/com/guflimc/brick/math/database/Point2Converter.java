package com.guflimc.brick.math.database;

import com.guflimc.brick.math.common.geometry.pos2.Point2;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@javax.persistence.Converter
public class Point2Converter implements AttributeConverter<Point2, String>, javax.persistence.AttributeConverter<Point2, String> {

    @Override
    public String convertToDatabaseColumn(Point2 attribute) {
        return Point2Serializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Point2 convertToEntityAttribute(String dbData) {
        return Point2Serializer.INSTANCE.deserialize(dbData);
    }
}