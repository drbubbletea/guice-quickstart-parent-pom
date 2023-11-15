package net.timeboxing.settings.guice;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import net.timeboxing.settings.Settings;
import net.timeboxing.settings.impl.MapSettings;

import java.util.Map;

public class MapSettingsModule extends AbstractModule {

    private final ImmutableMap<String, String> values;

    public MapSettingsModule(Map<String, String> properties) {
        values = ImmutableMap.copyOf(properties);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(Settings.class).toInstance(new MapSettings(values));
    }
}
