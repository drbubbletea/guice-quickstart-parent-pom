package net.timeboxing.guice.vaadin.guice.component.impl;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;

public class DefaultVaadinUser implements User {

    private final Integer id;

    @Inject
    public DefaultVaadinUser(@Assisted Integer id) {
        this.id = id;
    }

    @Override
    public Integer id() {
        return id;
    }
}
