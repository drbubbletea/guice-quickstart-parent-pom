package net.timeboxing.vaadin.event;

public interface VaadinComponentEventBus {

    <T extends ComponentEvent> ListenerRegistration listen(Class<T> event, VaadinComponentEventListener<T> listener);

    <T extends ComponentEvent> void send (T event);
}
