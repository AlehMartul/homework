package configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static Properties properties;

    public static void readProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(RESOURCES_PATH, "steam.properties")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getData(String data) {
        initializeProperties();
        return properties.getProperty(data);
    }

    private static void initializeProperties() {
        if (properties == null) {
            readProperties();
        }
    }
}