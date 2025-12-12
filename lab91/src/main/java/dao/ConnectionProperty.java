package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {

    public static final String CONFIG_NAME = "config.properties";
    public static final Properties PROPERTY_CONFIG = new Properties();

    static {
        try {
            ClassLoader classLoader = ConnectionProperty.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("config/" + CONFIG_NAME);
            if (is == null) {
                System.out.println("ERROR: config/config.properties NOT found in classpath");
            } else {
                PROPERTY_CONFIG.load(is);
                is.close();
                System.out.println("config/config.properties loaded");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}
