package net.timeboxing.adapter;

import java.util.Optional;

public interface Adapter {
    <T> Optional<T> adaptTo(Object adaptee, Class<T> desiredClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue);

}
