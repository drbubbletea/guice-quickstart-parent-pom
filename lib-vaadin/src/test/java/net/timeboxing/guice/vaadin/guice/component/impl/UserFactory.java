package net.timeboxing.guice.vaadin.guice.component.impl;

import com.google.inject.assistedinject.Assisted;

public interface UserFactory {

    User create(@Assisted Integer id);
}
