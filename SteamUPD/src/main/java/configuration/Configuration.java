package configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static Properties properties;
    private String localization;
    private String xmlPath;
    private static final Logger logger = LogManager.getLogger(Configuration.class);

    private static void readProperties() {
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

    public void setLocalization() {
        localization = System.getenv("localization");

        if (localization != null) {
            if (localization.equals("ru")) {
                logger.info("Localization is not ru");
                xmlPath = "testDataRu.xml";
            } else if (localization.equals("com")) {
                xmlPath = "testDataCom.xml";
                logger.info("Localization is not ru");
            } else {
                logger.info("Localization is not allowed: " + localization);
            }
        } else {
            System.out.println("Localization is not specified");
            logger.info("Localization is not specified");
        }
    }
}