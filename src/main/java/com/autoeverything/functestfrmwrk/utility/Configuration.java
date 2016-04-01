/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.utility;

import java.io.FileReader;
import java.util.Properties;

/**
 *
 * @author vinodyadav
 */
public class Configuration {

    private static Configuration configuration;
    private Properties prop;

    private Configuration() {
    }

    public static Configuration getInstance() throws Exception {
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                    configuration.init();
                }
            }
        }
        return configuration;
    }

    public void init() throws Exception {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("lib/config.properties");
            prop = new Properties();
            prop.load(fileReader);
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

    public String getProperty(String propName) {
        return prop.getProperty(propName);
    }
}
