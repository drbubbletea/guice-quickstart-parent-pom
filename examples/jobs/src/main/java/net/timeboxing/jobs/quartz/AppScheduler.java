package net.timeboxing.jobs.quartz;

import com.google.common.base.Strings;
import org.quartz.*;
import org.quartz.spi.MutableTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppScheduler {

    private final Logger LOG = LoggerFactory.getLogger(AppScheduler.class);

    private final Scheduler scheduler;
    private final Set<Class<? extends Job>> jobClasses;

    @Inject
    public AppScheduler(Scheduler scheduler, Set<Class<? extends Job>> jobClasses) {
        this.scheduler = scheduler;
        this.jobClasses = jobClasses;
    }

    public void startup() {
        try {
            LOG.info("Starting");
            scheduler.start();
            scheduleJobs();
        } catch (SchedulerException e) {
            throw new AppSchedulerException(e);
        }
    }

    public void shutdown() {
        try {
            LOG.info("Stopping");
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new AppSchedulerException(e);
        }
    }

    private void scheduleJobs() throws SchedulerException {
        for (Class<? extends Job> jobClass: jobClasses) {
            LOG.info("Scheduling job {}", jobClass.getName());
            JobSchedule schedule = jobClass.getAnnotation(JobSchedule.class);
            if (null != schedule) {
                JobDetail jobDetail = JobBuilder //
                        .newJob(jobClass) //
                        .withIdentity(jobClass.getName()) //
                        .build();
                Trigger trigger = getTrigger(jobClass.getName(), schedule);

                scheduler.scheduleJob(jobDetail, trigger);
            }
        }
    }

    private Trigger getTrigger(String jobName, JobSchedule schedule) {
        if (!Strings.isNullOrEmpty(schedule.cronExpression())) {
            return TriggerBuilder //
                    .newTrigger() //
                    .withSchedule(CronScheduleBuilder.cronSchedule(schedule.cronExpression())) //
                    .withIdentity(jobName) //
                    .build();
        } else {
            SimpleScheduleBuilder trigger = SimpleScheduleBuilder.simpleSchedule();
            trigger.withIntervalInMinutes(Long.valueOf(TimeUnit.MINUTES.convert(schedule.interval(), schedule.intervalUnit())).intValue());
            return TriggerBuilder //
                    .newTrigger() //
                    .withSchedule(trigger.repeatForever()) //
                    .withIdentity(jobName) //
                    .build();
        }
    }
}
