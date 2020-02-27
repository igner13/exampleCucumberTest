package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static String baseUrl;
    private static String authorizationHeader;
    private static Boolean propertiesLoaded = false;

    private PropertyReader() {
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public static void setProperties() {
        if (propertiesLoaded) {
            return;
        }
        Properties properties = getProperties();

        baseUrl = properties.getProperty("baseUrl");
        authorizationHeader = properties.getProperty("authorizationHeader");

        propertiesLoaded = true;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        InputStream propertiesStream = contextClassLoader.getResourceAsStream("app.properties");
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
