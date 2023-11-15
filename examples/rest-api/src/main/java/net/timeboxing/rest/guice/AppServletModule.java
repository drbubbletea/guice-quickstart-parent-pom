package net.timeboxing.rest.guice;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(AppRestServlet.class).in(Scopes.SINGLETON);
        serve("/*").with(AppRestServlet.class);
    }
}