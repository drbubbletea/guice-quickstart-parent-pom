package net.timeboxing.jobs.guice;

import com.google.inject.Injector;
import com.google.inject.Module;
import net.timeboxing.jobs.quartz.AppScheduler;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;

@WebListener
public class GuiceServletConfiguration extends GuiceResteasyBootstrapServletContextListener {

    private AppScheduler appScheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        appScheduler.startup();
    }

    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return List.of(new JobsModule());
    }

    @Override
    protected void withInjector(Injector injector) {
        super.withInjector(injector);
        appScheduler = injector.getInstance(AppScheduler.class);
    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent event) {
        super.contextDestroyed(event);
        appScheduler.shutdown();
    }


}
