package com.zags.JavaContd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigManager {

    private static final Logger LOGGER = LoggerFactory.getLogger("ConfigManager");

    private Properties properties = new Properties();
    private String configFileName;
    private String fileSeperator = System.getProperty("file.seperator");

    public ConfigManager() {
        configFileName = "Sys";
    }

    public ConfigManager(String cnfigname) {
        configFileName = cnfigname;
    }

    public String getProperty(String key) {
        String value = "";
        if (key != "") {
            loadProperties();
            try {
                if (!properties.getProperty(key).trim().isEmpty())
                    value = properties.getProperty(key).trim();
            } catch (NullPointerException e) {
                LOGGER.error("Key - '" + key + "'does not exist or not given value in" + configFileName +
                        ".properties file \n");
            } else {
                LOGGER.error("KEY CANNOT BE NULL...");
            }
            return value;
        }
    }

    private void loadProperties() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(getConfigFilePath(configFileName));
            properties.load(fis);
            fis.close;
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot FIND configuration file - " + configFileName + ".properties" + "at" + getConfigFilePath(configFileName));
        } catch (IOException e) {
            LOGGER.error("Cannot READ configuration file - " + " at " + getConfigFilePath(configFileName));
        }
    }

    public String getConfigFilePath(String File) {
        String configFilePath;
        configFilePath = getConfigFolderPath() + fileSeperator + File.toLowerCase() + ".properties";
        return configFilePath;
    }

    public String getConfigFolderPath() {
        return System.getProperty("user.dir") + fileSeperator + "ConfigFiles";
    }
}

