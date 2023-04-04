package com.guflimc.brick.math.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonTools {

    private static final Gson gson = new Gson();

    public static String serialize(Object object) {
        JsonObject json = (JsonObject) gson.toJsonTree(object);
        json.addProperty("type", object.getClass().getSimpleName());
        return json.toString();
    }

    @SafeVarargs
    public static <T> T deserialize(String data, Class<T> supertype, Class<? extends T>... subtypes) {
        JsonObject json = (JsonObject) JsonParser.parseString(data);
        if ( json.has("type") ) {
            String type = json.get("type").getAsString();
            for ( Class<? extends T> subtype : subtypes ) {
                if ( subtype.getSimpleName().equals(type) ) {
                    return supertype.cast(gson.fromJson(json, subtype));
                }
            }
        }

        throw new IllegalArgumentException("The data does not contain a valid sub-type.");
    }

}
