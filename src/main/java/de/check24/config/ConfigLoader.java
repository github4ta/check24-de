package de.check24.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading configuration from properties
 */
public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Configuration file not found: " + CONFIG_FILE);
                // Do not terminate execution, continue with empty properties
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Configuration loading error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get property value
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value with default value
     */
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get int value
     */
    public static int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get boolean value
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
}
