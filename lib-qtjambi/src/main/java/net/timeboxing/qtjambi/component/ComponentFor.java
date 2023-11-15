package net.timeboxing.qtjambi.component;

import net.timeboxing.adapter.CustomAdaptedFrom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@CustomAdaptedFrom(translation = ComponentForTranslation.class)
public @interface ComponentFor {

    Class<?> forClass();

    ComponentPurpose purpose() default ComponentPurpose.DEFAULT;

    String purposeValue() default "DEFAULT";
}
