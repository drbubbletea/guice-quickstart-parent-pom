package net.timeboxing.vaadin.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import net.timeboxing.vaadin.component.*;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class VaadinComponentModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinComponentModule.class);

    private final String packageToScan;

    private final TypeLiteral<ComponentCreatorKey> componentCreatorKeyTypeLiteral = TypeLiteral.get(ComponentCreatorKey.class);
    private final TypeLiteral<VaadinComponentCreator> componentCreatorTypeLiteral = TypeLiteral.get(VaadinComponentCreator.class);

    public VaadinComponentModule(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    @Override
    protected void configure() {
        requestStaticInjection(ComponentAdapter.class);

        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(ComponentFor.class);
        MapBinder<ComponentCreatorKey, VaadinComponentCreator> creators = MapBinder.newMapBinder(binder(), componentCreatorKeyTypeLiteral, componentCreatorTypeLiteral);
        for (Class<?> component : components) {
            LOG.debug("Found class {}", component.getCanonicalName());
            ComponentFor annotation = component.getAnnotation(ComponentFor.class);
            Class<?> forClass = annotation.forClass();
            ComponentPurpose purpose = annotation.purpose();
            ComponentCreatorKey key = new ComponentCreatorKey(forClass, purpose);
            VaadinComponentCreator creator = new VaadinComponentCreator(component);
            creators.addBinding(key).toInstance(creator);
            LOG.debug("Bound creator: {}", key);
        }
    }
}
