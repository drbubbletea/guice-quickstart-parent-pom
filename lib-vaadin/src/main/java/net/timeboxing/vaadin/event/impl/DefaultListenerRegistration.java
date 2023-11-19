package net.timeboxing.vaadin.event.impl;

import net.timeboxing.listener.ListenerRegistration;
import net.timeboxing.vaadin.event.ComponentEvent;
import net.timeboxing.vaadin.event.VaadinComponentEventListener;

import java.lang.ref.WeakReference;
import java.util.Map;

public class DefaultListenerRegistration implements ListenerRegistration {

    private final VaadinComponentEventListener<?> listener;
    private final WeakReference<VaadinComponentEventListener<?>> reference;

    private final Map<WeakReference<VaadinComponentEventListener<?>>, Class<? extends ComponentEvent>> listeners;

    public DefaultListenerRegistration(WeakReference<VaadinComponentEventListener<?>> reference, Map<WeakReference<VaadinComponentEventListener<?>>, Class<? extends ComponentEvent>> listeners) {
        this.reference = reference;
        this.listener = reference.get();
        this.listeners = listeners;
    }
    @Override
    public void unregister() {
        listeners.remove(reference);
    }
}
