package dev.swapi.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class PojoConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    private PojoConverter() {
    }

    public static <T> T convertMapToPojo(Map<String, Object> targetMap, Class<T> clazz) {
        return mapper.convertValue(targetMap, clazz);
    }
}
