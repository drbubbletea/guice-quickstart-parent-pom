package net.timeboxing.adapter.impl;

import com.google.inject.assistedinject.Assisted;

public interface DomainFactory {
    User create(@Assisted("name") String name);
}
