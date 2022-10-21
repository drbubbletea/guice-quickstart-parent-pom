package net.timeboxing.rest.guice;

import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class GuiceServletConfiguration extends GuiceResteasyBootstrapServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }

    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return List.of(new AppRestModule());
    }
}
