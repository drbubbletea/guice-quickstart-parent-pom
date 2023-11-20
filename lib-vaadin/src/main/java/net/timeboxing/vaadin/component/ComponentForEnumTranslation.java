package net.timeboxing.vaadin.component;

import net.timeboxing.adapter.CustomAdaptedFromTranslation;

public class ComponentForEnumTranslation extends CustomAdaptedFromTranslation<ComponentForEnum> {
    @Override
    public Class<?> from(ComponentForEnum instance) {
        return instance.forClass();
    }

    @Override
    public Class<?> to(ComponentForEnum instance) {
        return VaadinComponent.class;
    }

    @Override
    public Class<? extends Enum<?>> purposeEnum(ComponentForEnum instance) {
        return instance.purpose();
    }

    @Override
    public Object purposeValue(ComponentForEnum instance) {
        return instance.purposeValue().toString();
    }
}
