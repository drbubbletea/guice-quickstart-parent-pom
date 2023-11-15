package net.timeboxing.adapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate the constructor parameter of an AdaptedFrom target class to allow the source object of the adapter pattern,
 * the adaptee, to be available through an '@Inject'-annotated constructor.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Adaptee {
}
