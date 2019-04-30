package com.krzsz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    public static final PropertiesUtils INSTANCE = new PropertiesUtils();

    private PropertiesUtils() {
        Properties properties = new Properties();
        String propertyFile = "application.properties";

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(propertyFile);
        try {
            properties.load(stream);
        } catch ( IOException e) {
            System.out.println("There were problems loading property file.");
        }
        System.setProperties(properties);
    }


    public String getPropertyStringValue(String key) {
        Object obj = System.getProperties().get(key);
        return (String) obj;
    }

    public int getPropertyIntValue(String key){
        return Integer.parseInt(getPropertyStringValue(key));
    }
}
