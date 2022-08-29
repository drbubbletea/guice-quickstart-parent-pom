package net.timeboxing.settings.impl;

import java.util.Map;

/**
 * A settings implementation to make testing easier.
 */
public class MapSettings extends AbstractSettings {

    public MapSettings(Map<String, String> settings) {
        load(settings);
    }
}
