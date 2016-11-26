package com.netcracker.library.tools;

import com.netcracker.library.constants.PageConstants;

import java.util.ResourceBundle;

/**
 * Created by raumo0 on 24.11.16.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PageConstants.CONFIG_SOURCE);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
