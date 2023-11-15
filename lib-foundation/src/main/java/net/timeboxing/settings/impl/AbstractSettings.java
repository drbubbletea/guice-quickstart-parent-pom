package net.timeboxing.settings.impl;

import net.timeboxing.settings.Settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractSettings implements Settings {

    protected final Map<String, String> values = new HashMap<>();

    protected void load(Map<String, String> values) {
        // TODO: synchronization
        this.values.clear();
        this.values.putAll(values);
    }

    @Override
    public Optional<String> getString(String name) {
        String value = values.getOrDefault(name, null);
        if (null == value) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

    @Override
    public Optional<Integer> getInteger(String name) {
        String value = values.getOrDefault(name, null);
        if (null == value) {
            return Optional.empty();
        } else {
            return Optional.of(Integer.valueOf(value));
        }
    }

    @Override
    public Optional<String> getStringOrDefault(String name, String defaultValue) {
        String value = values.getOrDefault(name, defaultValue);
        return Optional.of(value);
    }

    @Override
    public Optional<Integer> getIntegerOrDefault(String name, Integer defaultValue) {
        String value = values.getOrDefault(name, null);
        if (null == value) {
            return Optional.of(defaultValue);
        } else {
            return Optional.of(Integer.valueOf(value));
        }
    }
}
