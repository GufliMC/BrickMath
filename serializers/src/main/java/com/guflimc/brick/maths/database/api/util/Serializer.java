package com.guflimc.brick.maths.database.api.util;

public interface Serializer<T> {

    T deserialize(String input);

    String serialize(T input);

}
