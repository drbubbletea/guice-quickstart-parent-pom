package net.timeboxing.settings.setting.impl;

public class StringSetting extends AbstractSetting<String> {
    public StringSetting(String value, String name) {
        super(value, name);
    }

    @Override
    protected String serialize() {
        return value;
    }

    @Override
    protected String deserialize(String value) {
        return value;
    }
}
