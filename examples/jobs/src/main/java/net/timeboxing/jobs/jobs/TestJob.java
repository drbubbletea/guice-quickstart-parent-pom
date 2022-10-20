package net.timeboxing.jobs.jobs;

import net.timeboxing.jobs.TestInjection;
import net.timeboxing.jobs.quartz.JobSchedule;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@JobSchedule(interval = 1, intervalUnit = TimeUnit.MINUTES)
public class TestJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(TestJob.class);
    private final TestInjection testInjection;

    @Inject
    public TestJob(TestInjection testInjection) {
        this.testInjection = testInjection;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("testing!");
        testInjection.test();
    }
}
