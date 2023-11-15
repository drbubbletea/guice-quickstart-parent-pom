package net.timeboxing.adapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate classes which are adapter targets.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AdaptedFrom {

    Class<?> from();

    Class<?> to();

    Class<? extends Enum<?>> purposeEnum() default AdapterPurpose.class;
    String purposeValue() default "DEFAULT"; // must match an enum value name
}
