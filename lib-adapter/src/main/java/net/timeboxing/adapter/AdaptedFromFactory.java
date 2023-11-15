package net.timeboxing.adapter;

public interface AdaptedFromFactory {

    Object get(Object source, Class<?> desiredClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue);

}
