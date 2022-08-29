package net.timeboxing.settings.setting.impl;

import net.timeboxing.settings.setting.Setting;

public abstract class AbstractSetting<T> implements Setting<T> {

    protected final T value;
    protected final String name;

    protected AbstractSetting(T value, String name) {
        this.value = value;
        this.name = name;
    }
    protected abstract String serialize();

    protected abstract T deserialize(String value);

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final T get() {
        return value;
    }
}
