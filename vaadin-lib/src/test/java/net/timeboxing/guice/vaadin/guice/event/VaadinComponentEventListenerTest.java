package net.timeboxing.guice.vaadin.guice.event;

import net.timeboxing.vaadin.event.ComponentEvent;
import net.timeboxing.vaadin.event.ListenerRegistration;
import net.timeboxing.vaadin.event.impl.DefaultVaadinComponentEventBus;
import net.timeboxing.vaadin.event.impl.NoEvent;
import net.timeboxing.vaadin.event.impl.YesEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class VaadinComponentEventListenerTest {

    @Test
    public void canUnregister() {
        DefaultVaadinComponentEventBus eventBus = new DefaultVaadinComponentEventBus();

        ListenerRegistration registration = eventBus.listen(YesEvent.class, this::runMe);

        Assertions.assertEquals(1, eventBus.analyzeAndCount());
        registration.unregister();
        Assertions.assertEquals(0, eventBus.analyzeAndCount());
    }


    @Test
    public void gcRemoves() {
        DefaultVaadinComponentEventBus eventBus = new DefaultVaadinComponentEventBus();

        ListenerRegistration registration = eventBus.listen(YesEvent.class, this::runMe);

        Assertions.assertEquals(1, eventBus.analyzeAndCount());
        registration = null;
        System.gc();
        Assertions.assertEquals(0, eventBus.analyzeAndCount());
    }

    @Test
    public void listenerInvokedMatchingEvent() {
        DefaultVaadinComponentEventBus eventBus = new DefaultVaadinComponentEventBus();
        AtomicBoolean modified = new AtomicBoolean(false);
        ListenerRegistration registration = eventBus.listen(YesEvent.class, event -> {
            modified.set(true);
        });
        eventBus.send(new YesEvent(null));
        Assertions.assertTrue(modified.get());
    }

    @Test
    public void listenerNotInvokedDifferentEvent() {
        DefaultVaadinComponentEventBus eventBus = new DefaultVaadinComponentEventBus();
        AtomicBoolean modified = new AtomicBoolean(false);
        ListenerRegistration registration = eventBus.listen(YesEvent.class, event -> {
            modified.set(true);
        });
        eventBus.send(new NoEvent(null));
        Assertions.assertFalse(modified.get());
    }

    private <T extends ComponentEvent> void runMe(T t) {
        /* NOOP */
    }
}
