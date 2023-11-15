package net.timeboxing.settings.setting;

public interface Setting<T> {

    T get();

    String name();
}
