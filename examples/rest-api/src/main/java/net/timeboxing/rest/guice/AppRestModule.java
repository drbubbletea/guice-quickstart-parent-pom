package net.timeboxing.rest.guice;

import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppRestModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(AppRestModule.class);

    @Override
    protected void configure() {
        install(new RestModule("net.timeboxing"));
        install(new AppServletModule());
    }
}
