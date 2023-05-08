package com.guflimc.brick.math.database;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import com.guflimc.brick.math.common.geometry.shape2d.Polygon;
import com.guflimc.brick.math.common.geometry.shape2d.Rectangle;
import com.guflimc.brick.math.common.geometry.shape2d.RegularHexagon;
import com.guflimc.brick.math.common.geometry.shape3d.PolyPrism;
import com.guflimc.brick.math.common.geometry.shape3d.RectPrism;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GsonTools {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new ClassTypeAdapterFactory())
            .create();

    public static String serialize(Object object) {
        return gson.toJsonTree(object).toString();
    }

    public static <T> T deserialize(String data, Class<T> type) {
        return gson.fromJson(data, type);
    }

    //

    private static class ClassTypeAdapterFactory implements TypeAdapterFactory {

        private final static String CLASS_NAME_PROPERTY = "__class_name__";

        private final static Map<Class<?>, String> typeToName = new HashMap<>();
        private final static Map<String, Class<?>> nameToType = new HashMap<>();

        private static void registerDirectType(Class<?> type) {
            typeToName.put(type, type.getSimpleName());
            nameToType.put(type.getSimpleName(), type);
        }

        static {
            registerDirectType(Vector2.class);
            registerDirectType(Location.class);
            registerDirectType(Vector3.class);
            registerDirectType(Polygon.class);
            registerDirectType(Rectangle.class);
            registerDirectType(RegularHexagon.class);
            registerDirectType(PolyPrism.class);
            registerDirectType(RectPrism.class);
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<JsonElement> jsonElementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapterFactory thiz = this;

            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    Class<T> srcType = (Class<T>) value.getClass();
                    TypeAdapter<T> delegate = gson.getDelegateAdapter(thiz, TypeToken.get(srcType));
                    JsonElement json = delegate.toJsonTree(value);
                    if (json instanceof JsonObject jo) {

                        String name = srcType.getName();
                        if (typeToName.containsKey(srcType)) {
                            name = typeToName.get(srcType);
                        }
                        jo.addProperty(CLASS_NAME_PROPERTY, name);
                    }
                    jsonElementAdapter.write(out, json);
                }

                @Override
                public T read(JsonReader in) throws IOException {
                    JsonElement json = jsonElementAdapter.read(in);
                    if (!(json instanceof JsonObject jo) || !jo.has(CLASS_NAME_PROPERTY)) {
                        TypeAdapter<T> delegate = gson.getDelegateAdapter(thiz, type);
                        return delegate.fromJsonTree(json);
                    }

                    String className = jo.get(CLASS_NAME_PROPERTY).getAsString();
                    try {
                        Class<T> type;
                        if (nameToType.containsKey(className)) {
                            type = (Class<T>) nameToType.get(className);
                        } else {
                            type = (Class<T>) Class.forName(className);
                        }

                        TypeAdapter<T> delegate = gson.getDelegateAdapter(thiz, TypeToken.get(type));
                        return delegate.fromJsonTree(json);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }

}
