package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.adapter.Adaptee;
import net.timeboxing.vaadin.component.ComponentFor;
import net.timeboxing.vaadin.component.VaadinComponent;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purposeValue = "EDIT")
public class UserEditComponent implements VaadinComponent {

    private final User user;
    private final Component component;

    @Inject
    public UserEditComponent(@Adaptee User user) {
        this.user = user;
        this.component = new Label("test");
    }

    public User user() {
        return user;
    }


    @Override
    public Component get() {
        return component;
    }
}
