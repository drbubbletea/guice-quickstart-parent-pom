package net.timeboxing.vaadin.component;

import com.google.common.base.Objects;

public class ComponentCreatorKey {

    private final Class<?> forClass;
    private final ComponentPurpose purpose;

    public ComponentCreatorKey(Class<?> forClass, ComponentPurpose purpose) {
        this.forClass = forClass;
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "ComponentCreatorKey{" +
                "forClass=" + forClass +
                ", purpose=" + purpose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentCreatorKey that = (ComponentCreatorKey) o;
        return Objects.equal(forClass, that.forClass) && purpose == that.purpose;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(forClass, purpose);
    }

    public Class<?> source() {
        return forClass;
    }

    public ComponentPurpose purpose() {
        return purpose;
    }
}
