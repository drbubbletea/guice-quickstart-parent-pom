package net.timeboxing.adapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates custom AdaptedFrom annotations.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAdaptedFrom {
    Class<? extends CustomAdaptedFromTranslation> translation();
}
