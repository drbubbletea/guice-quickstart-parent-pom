package net.timeboxing.adapter;

/**
 * When using Guice AOP the class may be enhanced by Guice. That complicates things like checking class names.
 */
public class PossiblyEnhancedClass {

    private final Class<?> clazz;

    public PossiblyEnhancedClass(Class<?> clazz) {
        this.clazz = clazz;
    }
    public PossiblyEnhancedClass(Object object) {
        this.clazz = object.getClass();
    }

    public Class<?>[] interfaces() {
        if (isEnhanced()) {
            return clazz.getSuperclass().getInterfaces();
        } else {
            return clazz.getInterfaces();
        }
    }

    public Class<?> realClass() {
        if (isEnhanced()) {
            return clazz.getSuperclass();
        } else {
            return clazz;
        }
    }

    private boolean isEnhanced() {
        return clazz.getName().contains("EnhancerByGuice");
    }
}
