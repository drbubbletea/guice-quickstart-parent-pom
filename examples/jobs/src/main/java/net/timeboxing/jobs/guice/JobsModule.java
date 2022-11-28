package net.timeboxing.jobs.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import net.timeboxing.jobs.TestInjection;
import net.timeboxing.jobs.quartz.AppScheduler;
import net.timeboxing.jobs.quartz.GuiceJobFactory;
import net.timeboxing.jobs.rest.TestPath;
import net.timeboxing.rest.guice.RestModule;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

public class JobsModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(JobsModule.class);

    @Override
    protected void configure() {
        install(new RestModule("net.timeboxing"));
        install(new AppServletModule());
        bind(AppScheduler.class).in(Scopes.SINGLETON);
        bind(JobFactory.class).to(GuiceJobFactory.class).in(Scopes.SINGLETON);
        bind(TestInjection.class).in(Scopes.SINGLETON);

        Reflections reflections = new Reflections("net.timeboxing", Scanners.SubTypes);
        Multibinder<Class<? extends Job>> jobs = Multibinder.newSetBinder(binder(), new TypeLiteral<Class<? extends Job>>() {});
        for (Class<? extends Job> job: reflections.getSubTypesOf(Job.class)) {
            LOG.debug("Binding job {}", job.getName());
            jobs.addBinding().toInstance(job);
        }
    }

    @Singleton
    @Provides
    Scheduler scheduler(JobFactory jobFactory) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.setJobFactory(jobFactory);
        return scheduler;
    }
}
