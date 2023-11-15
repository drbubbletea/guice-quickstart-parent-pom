package net.timeboxing.adapter;

/**
 * Custom annotations may have more applicable field names to the problem they are trying to solve.
 * This allows us our adapter module to interpret a given annotation.
 */
public abstract class CustomAdaptedFromTranslation<T> {

    public abstract Class<?> from(T instance);

    public abstract Class<?> to(T instance);
    public abstract Class<? extends Enum<?>> purposeEnum(T instance);
    public abstract Object purposeValue(T instance);
}
