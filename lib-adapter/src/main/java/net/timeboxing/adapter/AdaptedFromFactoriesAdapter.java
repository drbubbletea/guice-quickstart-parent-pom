package net.timeboxing.adapter;

import javax.inject.Inject;
import java.util.Optional;

public class AdaptedFromFactoriesAdapter implements Adapter {

    private final AdaptedFromFactory factory;

    @Inject
    public AdaptedFromFactoriesAdapter(AdaptedFromFactory factory) {
        this.factory = factory;
    }

    @Override
    public <T> Optional<T> adaptTo(Object adaptee, Class<T> desiredClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue) {
        Optional<T> result = (Optional<T>) factory.get(adaptee, desiredClass, purposeEnum, purposeValue.toString());
        return result;
    }
}
