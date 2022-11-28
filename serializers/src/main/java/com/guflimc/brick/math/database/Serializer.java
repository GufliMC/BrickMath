package com.guflimc.brick.math.database;

public interface Serializer<T> {

    T deserialize(String input);

    String serialize(T input);

}
