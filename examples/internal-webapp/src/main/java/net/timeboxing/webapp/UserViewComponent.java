package net.timeboxing.webapp;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import net.timeboxing.vaadin.component.ComponentFor;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.Source;
import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.VaadinComponentEventBus;
import net.timeboxing.vaadin.event.impl.ConfirmEvent;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purpose = ComponentPurpose.VIEW)
public class UserViewComponent implements VaadinComponent {

    private final User user;
    private final ComponentPurpose purpose;

    private final VerticalLayout layout;

    private final GreetService greetService;

    private final VaadinComponentEventBus eventBus;

    @Inject
    public UserViewComponent(@Source User user, ComponentPurpose purpose, GreetService greetService, VaadinComponentEventBus eventBus) {
        this.eventBus = eventBus;
        this.user = user;
        this.purpose = purpose;
        this.layout = new VerticalLayout();
        this.greetService = greetService;
        layout.add(new Label("Testing"));
        eventBus.send(new ConfirmEvent(this));
    }
    @Override
    public Component get() {
        return layout;
    }
}
