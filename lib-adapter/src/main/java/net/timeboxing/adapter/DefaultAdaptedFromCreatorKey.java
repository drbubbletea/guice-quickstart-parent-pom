package net.timeboxing.adapter;

import java.util.Objects;

public class DefaultAdaptedFromCreatorKey implements AdaptedFromCreatorKey {

    private final Class<?> sourceClass;
    private final Class<?> desiredClass;
    private final Class<? extends Enum<?>> purposeEnum;
    private final Object purposeValue;

    public DefaultAdaptedFromCreatorKey(Class<?> sourceClass, Class<?> desiredClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue) {
        this.sourceClass = sourceClass;
        this.desiredClass = desiredClass;
        this.purposeEnum = purposeEnum;
        this.purposeValue = purposeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultAdaptedFromCreatorKey that = (DefaultAdaptedFromCreatorKey) o;
        return Objects.equals(sourceClass, that.sourceClass) && Objects.equals(desiredClass, that.desiredClass) && Objects.equals(purposeEnum, that.purposeEnum) && Objects.equals(purposeValue, that.purposeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceClass, desiredClass, purposeEnum, purposeValue);
    }

    @Override
    public String toString() {
        return "DefaultAdaptedFromCreatorKey{" +
                "sourceClass=" + sourceClass +
                ", desiredClass=" + desiredClass +
                ", purposeEnum=" + purposeEnum +
                ", purposeValue=" + purposeValue +
                '}';
    }

    public Class<?> sourceClass() {
        return sourceClass;
    }

    public Class<? extends Enum<?>> purposeEnum() {
        return purposeEnum;
    }

    public Object purposeValue() {
        return purposeValue;
    }

    public Class<?> desiredClass() {
        return desiredClass;
    }
}
