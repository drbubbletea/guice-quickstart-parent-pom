package net.timeboxing.webapp;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.guice.annotation.UIScope;
import net.timeboxing.listener.ListenerRegistration;
import net.timeboxing.settings.Settings;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.VaadinComponentEventBus;
import net.timeboxing.vaadin.event.impl.ConfirmEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;

/**
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */

@UIScope
public class MainView extends VerticalLayout {

    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    private final VaadinComponentEventBus eventBus;
    private ListenerRegistration registration;

    @Inject
    public MainView(GreetService greetService, Settings settings, VaadinComponentEventBus eventBus) {
        this.eventBus = eventBus;
        registration = eventBus.listen(ConfirmEvent.class, event -> {
            LOG.info("invoked!");
        });
        // Use TextField for standard text input
        TextField textField = new TextField("Your name");
        textField.addThemeName("bordered");
        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello", e -> Notification
                .show(greetService.greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in
        // shared-styles.css.
        addClassName("centered-content");

        add(textField, button);

        DefaultUser user = new DefaultUser(123);
        Optional<VaadinComponent> component = user.adaptTo(VaadinComponent.class, ComponentPurpose.VIEW);
        add(component.orElseThrow().get());

        Optional<String> value = settings.getString("somekey");
        if (!"somevalue".equals(value.orElseThrow())) {
            throw new RuntimeException("Failed to load properties");
        }
    }
}
