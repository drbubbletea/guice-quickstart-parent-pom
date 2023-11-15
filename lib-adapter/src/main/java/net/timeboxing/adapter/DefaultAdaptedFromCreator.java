package net.timeboxing.adapter;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class DefaultAdaptedFromCreator implements AdaptedFromCreator {

    private final Class<?> adaptedFromClass;
    private final Class<? extends Enum<?>> purposeEnum;
    private final Object purposeValue;
    protected final Constructor<?> constructor;
    private final Type[] genericTypes;
    private final Annotation[][] parameterAnnotations;
    private Injector injector;

    @Inject
    public void initialize(Injector injector) {
        this.injector = injector;
    }


    public DefaultAdaptedFromCreator(Class<?> adaptedFromClass, Class<? extends Enum<?>> purposeEnum, Object purposeValue) {
        this.adaptedFromClass = adaptedFromClass;
        this.purposeEnum = purposeEnum;
        this.purposeValue = purposeValue;
        this.constructor = getConstructor();
        this.genericTypes = constructor.getGenericParameterTypes();
        this.parameterAnnotations = constructor.getParameterAnnotations();
    }

    @Override
    public Object create(Object source) {
        return get(source);
    }


    protected Object[] getParameters(Object source) {
        Object[] parameters = new Object[constructor.getParameterCount()];
        for (int i = 0; i < parameters.length; i++) {
            boolean found = false;
            for (Annotation annotation: parameterAnnotations[i]) {
                if (Adaptee.class == annotation.annotationType()) {
                    parameters[i] = source;
                    found = true;
                    break;
                } else if (Purpose.class == annotation.annotationType()) {
                    Object enumPurposeValue = null;
                    Enum[] values = purposeEnum.getEnumConstants();
                    for (Enum value: values) {
                        if (value.name().equals(purposeValue)) {
                            enumPurposeValue = value;
                            break;
                        }
                    }
                    if (null == enumPurposeValue) {
                        throw new AdaptException("Failed to find enum with value" + purposeValue);
                    }
                    parameters[i] = enumPurposeValue;
                    found = true;
                    break;
                }
            }
            if (!found) {
                parameters[i] = injector.getInstance(Key.get(TypeLiteral.get(genericTypes[i])));
            }
        }
        return parameters;
    }
    protected Constructor getConstructor() {
        for (Constructor<?> ctor: adaptedFromClass.getConstructors()) {
            if (ctor.isAnnotationPresent(Inject.class)) {
                return ctor;
            }
        }
        throw new AdaptException(String.format("No constructor annotated with javax.inject.Inject found in %s", adaptedFromClass.getName()));
    }

    protected Object get(Object source) {
        try {
            Object[] parameters = getParameters(source);
            Object instance = constructor.newInstance(parameters);
            // handle any injection outside of the constructor
            injector.injectMembers(instance);
            return instance;
        } catch (Exception e) {
            throw new AdaptException("Failed to create component instance. Possibly forgot to annotate @Adaptee or @Purpose on the source constructor parameter?", e);
        }
    }
}
