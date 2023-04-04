package com.guflimc.brick.math.database;

import com.google.gson.Gson;

public interface Serializer<T> {

    T deserialize(String input);

    String serialize(T input);

}
