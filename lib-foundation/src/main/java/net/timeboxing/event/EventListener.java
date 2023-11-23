package net.timeboxing.event;

/**
 * Simmilar to net.timeboxing.vaadin.event.VaadinComponentEventListener but not related to ComponentEvents
 */
@FunctionalInterface
public interface EventListener<T> {
    void onEvent(T event);
}
