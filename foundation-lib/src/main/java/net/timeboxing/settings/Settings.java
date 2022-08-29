package net.timeboxing.settings;

import java.util.Optional;

/**
 * TODO: reconsider how we get types of settings
 */
public interface Settings {

    Optional<String> getString(String name);
    Optional<String> getStringOrDefault(String name, String defaultValue);
    Optional<Integer> getInteger(String name);
    Optional<Integer> getIntegerOrDefault(String name, Integer defaultValue);
}
