package net.timeboxing.adapter.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import net.timeboxing.adapter.AdaptException;
import net.timeboxing.adapter.AdaptedFrom;
import net.timeboxing.adapter.AdaptedFromFactoriesAdapter;
import net.timeboxing.adapter.AdaptedFromFactory;
import net.timeboxing.adapter.Adapter;
import net.timeboxing.adapter.CustomAdaptedFrom;
import net.timeboxing.adapter.CustomAdaptedFromTranslation;
import net.timeboxing.adapter.DefaultAdaptedFromCreator;
import net.timeboxing.adapter.DefaultAdaptedFromCreatorKey;
import net.timeboxing.adapter.DefaultAdaptedFromFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class AdapterModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(AdapterModule.class);

    private final List<String> packagesToScan;
    private final TypeLiteral<DefaultAdaptedFromCreatorKey> creatorKeyTypeLiteral = TypeLiteral.get(DefaultAdaptedFromCreatorKey.class);
    private final TypeLiteral<DefaultAdaptedFromCreator> creatorTypeLiteral = TypeLiteral.get(DefaultAdaptedFromCreator.class);


    public AdapterModule(String... packagesToScan) {
        this.packagesToScan = new ArrayList<>(List.of(packagesToScan));
    }
    @Override
    protected void configure() {
        MapBinder<DefaultAdaptedFromCreatorKey, DefaultAdaptedFromCreator> defaultCreators = MapBinder.newMapBinder(binder(), creatorKeyTypeLiteral, creatorTypeLiteral);
        // TODO: there should be a way to specify a list of package prefixes with Reflections instead of a for loop for scanning here
        for (String packageToScan: packagesToScan) {
            LOG.info("Scanning package {}", packageToScan);
            Reflections reflections = new Reflections(packageToScan);
            {
                Set<Class<?>> adaptedFromClasses = reflections.getTypesAnnotatedWith(AdaptedFrom.class);

                for (Class<?> adaptedFrom: adaptedFromClasses) {
                    LOG.debug("Found AdaptedFrom target {}", adaptedFrom.getCanonicalName());
                    AdaptedFrom annotation = adaptedFrom.getAnnotation(AdaptedFrom.class);
                    DefaultAdaptedFromCreatorKey key = new DefaultAdaptedFromCreatorKey(annotation.from(), annotation.to(), annotation.purposeEnum(), annotation.purposeValue());
                    DefaultAdaptedFromCreator creator = new DefaultAdaptedFromCreator(adaptedFrom, annotation.purposeEnum(), annotation.purposeValue());
                    defaultCreators.addBinding(key).toInstance(creator);
                    LOG.debug("Bound creator: {}", key);
                }
            }

            Set<Class<?>> customAdaptedFromAnnotations = reflections.getTypesAnnotatedWith(CustomAdaptedFrom.class);

            for (Class<?> customAdaptedFromAnnotation: customAdaptedFromAnnotations) {
                LOG.info("Found custom AdaptedFrom annotation {}", customAdaptedFromAnnotation.getCanonicalName());
                CustomAdaptedFrom customAdaptedFrom = customAdaptedFromAnnotation.getAnnotation(CustomAdaptedFrom.class);
                if (null != customAdaptedFrom) {
                    if (null == customAdaptedFrom.translation()) {
                        throw new AdaptException("Annotation missing CustomAdaptedFromTranslation " + customAdaptedFrom.getClass().getCanonicalName());
                    } else {
                        LOG.info("Using translation class {}", customAdaptedFrom.translation());
                        CustomAdaptedFromTranslation translation;
                        try {
                            translation = (CustomAdaptedFromTranslation) customAdaptedFrom.translation().getConstructors()[0].newInstance();
                        } catch (Exception e) {
                            throw new AdaptException("Failed to create translator instance for class" + customAdaptedFrom.translation().getClass().getCanonicalName());
                        }

                        Set<Class<?>> customAdaptedFromClasses = reflections.getTypesAnnotatedWith(customAdaptedFromAnnotation.asSubclass(Annotation.class));

                        for (Class<?> adaptedFrom: customAdaptedFromClasses) {
                            LOG.debug("Found class {}", adaptedFrom.getCanonicalName());
                            Annotation[] annotations = adaptedFrom.getAnnotations();
                            Annotation annotation = Arrays.stream(annotations).filter(t -> t.annotationType().equals(customAdaptedFromAnnotation)).findFirst().get();
                            DefaultAdaptedFromCreatorKey key = new DefaultAdaptedFromCreatorKey(translation.from(annotation), translation.to(annotation), translation.purposeEnum(annotation), translation.purposeValue(annotation));
                            DefaultAdaptedFromCreator creator = new DefaultAdaptedFromCreator(adaptedFrom, translation.purposeEnum(annotation), translation.purposeValue(annotation));
                            defaultCreators.addBinding(key).toInstance(creator);
                            LOG.debug("Bound creator: {}", creator);
                            LOG.debug("...to key: {}", key);
                        }
                    }
                }
            }
        }


        bind(Adapter.class).to(AdaptedFromFactoriesAdapter.class).in(Scopes.SINGLETON);
        bind(AdaptedFromFactory.class).to(DefaultAdaptedFromFactory.class).in(Scopes.SINGLETON);
        LOG.info("Complete");
    }
}
