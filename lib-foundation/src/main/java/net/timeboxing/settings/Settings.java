package net.timeboxing.settings;

import java.util.Optional;

public interface Settings {

    Optional<String> getString(String name);
    Optional<String> getStringOrDefault(String name, String defaultValue);
    Optional<Integer> getInteger(String name);
    Optional<Integer> getIntegerOrDefault(String name, Integer defaultValue);
}
