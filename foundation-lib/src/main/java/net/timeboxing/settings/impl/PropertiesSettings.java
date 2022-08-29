package net.timeboxing.settings.impl;

import com.google.common.collect.Maps;
import net.timeboxing.settings.SettingsException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesSettings extends AbstractFileSettings {

    public PropertiesSettings(String fileName) {
        InputStream inputStream = null;
        if (fileName.startsWith("classpath:")) {
            String actualFilename = fileName.substring("classpath:".length());
            inputStream = getClass().getResourceAsStream(actualFilename);
            if (inputStream == null) {
                throw new SettingsException(String.format("Classpath resource '%s' not found", actualFilename));
            }
        } else {
            try {
                inputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                throw new SettingsException(String.format("File not found '%s'", fileName), e);
            }
        }

        Properties properties = new Properties();
        try (InputStream input = inputStream) {
            properties.load(input);
            load(Maps.fromProperties(properties));
        } catch (IOException e) {
            throw new SettingsException("Failed to load properties file", e);
        }
    }
}
