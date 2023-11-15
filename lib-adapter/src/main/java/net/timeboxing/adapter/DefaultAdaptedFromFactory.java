package net.timeboxing.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;
import java.util.Optional;

public class DefaultAdaptedFromFactory implements AdaptedFromFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAdaptedFromFactory.class);

    private final Provider<Map<DefaultAdaptedFromCreatorKey, DefaultAdaptedFromCreator>> creatorsProvider;

    @Inject
    public DefaultAdaptedFromFactory(Provider<Map<DefaultAdaptedFromCreatorKey, DefaultAdaptedFromCreator>> creatorsProvider) {
        this.creatorsProvider = creatorsProvider;
    }

    @Override
    public Optional<Object> get(Object source, Class<?> desiredClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue) {
        Map<DefaultAdaptedFromCreatorKey, DefaultAdaptedFromCreator> map = creatorsProvider.get();
        // TODO: remove after debugging
        if (null != map) {
            LOG.info("Map size: {}", map.size());
        }
        PossiblyEnhancedClass pec = new PossiblyEnhancedClass(source);

        AdaptedFromCreatorKey key = new DefaultAdaptedFromCreatorKey(pec.realClass(), desiredClass, purposeEnum, purposeValue);
        LOG.debug("Looking for creator for {}", key);

        Map<DefaultAdaptedFromCreatorKey, DefaultAdaptedFromCreator> creators = creatorsProvider.get();
        AdaptedFromCreator creator = creatorsProvider.get().getOrDefault(key, null);
        Object result = null;
        if (creator != null) {
            result = creator.create(source);
        }
        if (result == null) {
            // find creator
            Class<?>[] interfaces = pec.interfaces();
            for (int i = 0; i < interfaces.length; i++) {
                key = new DefaultAdaptedFromCreatorKey(interfaces[i], desiredClass, purposeEnum, purposeValue);
                LOG.debug("Looking for creator for {}", key);
                creator = creatorsProvider.get().getOrDefault(key, null);
                if (creator != null) {
                    result = creator.create(source);
                    break;
                }
            }
        }
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
