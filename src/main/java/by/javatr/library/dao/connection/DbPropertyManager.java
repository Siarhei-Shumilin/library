package by.javatr.library.dao.connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPropertyManager {

    private final static Logger LOGGER = Logger.getLogger(DbPropertyManager.class);

    private DbPropertyManager() {
    }

    public static String getProperty(String key) {
        Properties property = new Properties();
        String conf = null;
        try {
            InputStream inputStream = DbPropertyManager.class.getClassLoader().getResourceAsStream("config.properties");
            property.load(inputStream);
            conf = property.getProperty(key);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return conf;
    }

}
