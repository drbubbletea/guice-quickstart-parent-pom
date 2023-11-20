package net.timeboxing.webapp;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import net.timeboxing.adapter.Adaptee;
import net.timeboxing.adapter.Purpose;
import net.timeboxing.resource.ClassResource;
import net.timeboxing.vaadin.component.*;
import net.timeboxing.vaadin.event.VaadinComponentEventBus;
import net.timeboxing.vaadin.event.impl.ConfirmEvent;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purpose = ComponentPurpose.VIEW)
public class UserViewComponent implements VaadinComponent {

    private final User user;
    private final ComponentPurpose purpose;

    private final VerticalLayout layout;

    private final GreetService greetService;

    private final VaadinComponentEventBus eventBus;

    private final String example = ClassResource.get("test.txt");

    @Inject
    public UserViewComponent(@Adaptee User user, @Purpose ComponentPurpose purpose, GreetService greetService, VaadinComponentEventBus eventBus) {
        this.eventBus = eventBus;
        this.user = user;
        this.purpose = purpose;
        this.layout = new VerticalLayout();
        this.greetService = greetService;
        layout.add(new Label("Testing"));
        eventBus.send(new ConfirmEvent(this));
        if (!StringUtils.equals("testing123", example) ) {
            throw new RuntimeException("Not as expected");
        }
    }
    @Override
    public Component get() {
        return layout;
    }
}
