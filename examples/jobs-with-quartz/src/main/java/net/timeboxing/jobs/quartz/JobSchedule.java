package net.timeboxing.jobs.quartz;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface JobSchedule {

    int interval() default 0;

    TimeUnit intervalUnit() default TimeUnit.MINUTES;

    String cronExpression() default "";
}
