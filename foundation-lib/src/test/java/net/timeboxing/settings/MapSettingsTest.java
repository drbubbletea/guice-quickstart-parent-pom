package net.timeboxing.settings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.settings.guice.MapSettingsModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapSettingsTest {

    @Test
    void canGetProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("database.name", "test");
        properties.put("database.username", "tester");
        properties.put("database.password", "secretpassword");
        properties.put("database.connections", "20");

        Injector injector = Guice.createInjector(new MapSettingsModule(properties));
        Settings settings = injector.getInstance(Settings.class);
        Assertions.assertEquals("test", settings.getString("database.name").orElseThrow());
        Assertions.assertEquals("tester", settings.getString("database.username").orElseThrow());
        Assertions.assertEquals("secretpassword", settings.getString("database.password").orElseThrow());
        Assertions.assertEquals(Integer.valueOf(20), settings.getInteger("database.connections").orElseThrow());
    }
}
