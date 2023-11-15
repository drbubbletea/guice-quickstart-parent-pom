package net.timeboxing.adapter;

import java.util.Optional;

public interface Adaptable {

    @AdaptMethod
    default <T> Optional<T> adaptTo(Class<T> desiredClass) {
        return Optional.empty();
    }
    @AdaptMethod
    default <T> Optional<T> adaptTo(Class<T> desiredClass, Object purposeValue) {
        return Optional.empty();
    }
    @AdaptMethod
    default <T> Optional<T> adaptTo(Class<T> desiredClass, Object purposeEnum, Object purposeValue) {
        return Optional.empty();
    }
}
