package net.timeboxing.adapter.impl;

import net.timeboxing.adapter.CustomAdaptedFromTranslation;
import org.junit.platform.commons.util.StringUtils;

public class FooBarAdaptedFromTranslation extends CustomAdaptedFromTranslation<FooBarFor> {
    @Override
    public Class<?> from(FooBarFor instance) {
        return instance.from();
    }

    @Override
    public Class<?> to(FooBarFor instance) {
        return instance.to();
    }

    @Override
    public Class<? extends Enum<?>> purposeEnum(FooBarFor instance) {
        return instance.purposeEnum();
    }

    @Override
    public Object purposeValue(FooBarFor instance) {
        if (StringUtils.isBlank(instance.purposeValue())) {
            return FooBar.FOO;
        } else {
            return instance.purposeValue();
        }
    }
}
