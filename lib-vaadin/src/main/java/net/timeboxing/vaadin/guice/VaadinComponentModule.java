package net.timeboxing.vaadin.guice;

import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaadinComponentModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinComponentModule.class);

    private final String packageToScan;

    public VaadinComponentModule(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    @Override
    protected void configure() {
//        requestStaticInjection(ComponentAdapter.class);
//
//        Reflections reflections = new Reflections(packageToScan);
//        Set<Class<?>> components = reflections.getTypesAnnotatedWith(ComponentFor.class);
//        MapBinder<DefaultComponentCreatorKey, DefaultVaadinComponentCreator> defaultCreators = MapBinder.newMapBinder(binder(), componentCreatorKeyTypeLiteral, componentCreatorTypeLiteral);
//        MapBinder<CustomComponentCreatorKey, CustomVaadinComponentCreator> customCreators = MapBinder.newMapBinder(binder(), customComponentCreatorKeyTypeLiteral, customVaadinComponentCreatorTypeLiteral);
//        for (Class<?> component : components) {
//            LOG.debug("Found class {}", component.getCanonicalName());
//            ComponentFor annotation = component.getAnnotation(ComponentFor.class);
//            Class<?> forClass = annotation.forClass();
//            if (Strings.isNullOrEmpty(annotation.purposeType()) && Strings.isNullOrEmpty(annotation.purposeValue())) {
//                ComponentPurpose purpose = annotation.purpose();
//                DefaultComponentCreatorKey key = new DefaultComponentCreatorKey(forClass, purpose);
//                DefaultVaadinComponentCreator creator = new DefaultVaadinComponentCreator(component);
//                defaultCreators.addBinding(key).toInstance(creator);
//                LOG.debug("Bound creator: {}", key);
//            } else {
//                String purpose = annotation.purposeType();
//                String value = annotation.purposeValue();
//                CustomComponentCreatorKey key = new CustomComponentCreatorKey(forClass, purpose, value);
//                CustomVaadinComponentCreator creator = new CustomVaadinComponentCreator(component);
//                customCreators.addBinding(key).toInstance(creator);
//                LOG.debug("Bound creator: {}", key);
//            }
//        }
    }
}
