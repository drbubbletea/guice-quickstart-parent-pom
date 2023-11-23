package net.timeboxing;

import javax.inject.Provider;

public interface CurrentValue<T> extends Provider<T> {

    void set(T value);
}
