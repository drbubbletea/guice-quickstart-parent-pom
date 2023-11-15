package net.timeboxing.vaadin.component;

import com.vaadin.flow.component.Component;


public interface VaadinComponent {

    /**
     * Get the Vaadin component class associated with this object.
     * @return
     */
    Component get();

    /**
     * Set the parent of this class.
     */
    default void addParent(VaadinComponent parent) {
        /* NOOP */
    }
}
