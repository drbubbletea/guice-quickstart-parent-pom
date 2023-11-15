package net.timeboxing.adapter.impl;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;

public class DefaultUser implements User {

    private final String name;

    @Inject
    public DefaultUser(@Assisted("name") String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
