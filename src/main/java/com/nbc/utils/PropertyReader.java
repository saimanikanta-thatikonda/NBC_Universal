package com.nbc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private PropertyReader()
    {

    }
    /**
     * This method returns the property value of field
     *
     * @param filePath     - path of the property file
     * @param propertyName - name of the property field
     * @return - returns the string value of a property
     */
    public static String getProperty(String filePath, String propertyName){
        Properties prop = null;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return prop.getProperty(propertyName).trim();
    }
}
