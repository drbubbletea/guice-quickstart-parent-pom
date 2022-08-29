package net.timeboxing.settings.guice;

import com.google.inject.AbstractModule;
import net.timeboxing.settings.Settings;
import net.timeboxing.settings.impl.PropertiesSettings;

public class PropertiesSettingsModule extends AbstractModule {

    private final String fileName;

    public PropertiesSettingsModule(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void configure() {
        super.configure();
        // TODO: support check for updates & event handling for updates
        PropertiesSettings settings = new PropertiesSettings(fileName);
        bind(Settings.class).toInstance(settings);
    }
}
