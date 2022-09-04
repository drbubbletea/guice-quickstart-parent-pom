package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.vaadin.component.ComponentFor;
import net.timeboxing.vaadin.component.VaadinComponent;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purposeType = "TEST", purposeValue = "EXAMPLE")
public class CustomUserTestExampleComponent implements VaadinComponent {

    @Inject
    public CustomUserTestExampleComponent() {
        /* NOOP */
    }
    private final Component component = new Label("testing");
    @Override
    public Component get() {
        return component;
    }
}
