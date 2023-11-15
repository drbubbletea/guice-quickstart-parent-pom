package net.timeboxing.adapter.impl;

import net.timeboxing.adapter.CustomAdaptedFrom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@CustomAdaptedFrom(translation = FooBarAdaptedFromTranslation.class)
public @interface FooBarFor {

    Class<?> from();

    Class<?> to();

    Class<? extends Enum<?>> purposeEnum() default FooBar.class;
    String purposeValue() default "FOO";
}
