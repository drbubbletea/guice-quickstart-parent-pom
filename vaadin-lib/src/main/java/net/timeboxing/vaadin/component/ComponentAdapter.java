package net.timeboxing.vaadin.component;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Optional;

/**
 * Static implementation of the adapter pattern for our Vaadin components.
 */
public class ComponentAdapter {


    private static Provider<DefaultVaadinComponentFactory> factoryProvider;
    private static Provider<CustomVaadinComponentFactory> customFactoryProvider;

    private ComponentAdapter() {
        /* NOOP */
    }

    @Inject
    public static void initialize(Provider<DefaultVaadinComponentFactory> provider, Provider<CustomVaadinComponentFactory> customProvider) {
        factoryProvider = provider;
        customFactoryProvider = customProvider;
    }

    /**
     * Find a suitable Component based on the ComponentPurpose value provided.
     */
    public static Optional<VaadinComponent> adapt(Object source, ComponentPurpose purpose) {
        if (factoryProvider == null) {
            throw new ComponentAdapterException("Default component factory not initialized");
        }
        return factoryProvider.get().get(source, purpose);
    }

    public static Optional<VaadinComponent> adapt(Object source, String type, String purpose) {
        if (factoryProvider == null) {
            throw new ComponentAdapterException("Custom component factory not initialized");
        }
        return customFactoryProvider.get().get(source, type, purpose);
    }

}
