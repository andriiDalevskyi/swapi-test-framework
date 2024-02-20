package dev.swapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTY_FILENAME = "config.properties";

    static {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(PROPERTY_FILENAME)) {
            PROPERTIES.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("App can't find `" + PROPERTY_FILENAME + "` file. Pls create it");
        }
    }

    private PropertyReader() {
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("url.base");
    }

    public static String getPlanetsUrl() {
        return PROPERTIES.getProperty("url.planets");
    }

    public static String getPeopleUrl() {
        return PROPERTIES.getProperty("url.people");
    }
}
