package com.automationtest.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.automationtest.utils.constants.Constants.*;
import static com.automationtest.utils.constants.Messages.*;
import static com.automationtest.utils.constants.Paths.propertiesPath;

public class PropertiesReader {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public Optional<Properties> getPropValues(String nameFileProperties) {
        Properties properties = new Properties();
        String propFileName = propertiesPath() + nameFileProperties;

        try (InputStream inputStream = Files.newInputStream(Paths.get(propFileName))) {
            properties.load(inputStream);
            return Optional.of(properties);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public String getProperty(String property, String nameFileProperties) {
        InputStream imputStream = null;
        Properties properties = new Properties();
        try {
            imputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(PROPERTIES + File.separator + nameFileProperties);
            properties.load(imputStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format(ERROR_READ_PROP, property, nameFileProperties), e);
        } finally {
            if (imputStream != null) {
                try {
                    imputStream.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, ERROR, e);
                }
            }
        }
        return properties.getProperty(property);
    }
}