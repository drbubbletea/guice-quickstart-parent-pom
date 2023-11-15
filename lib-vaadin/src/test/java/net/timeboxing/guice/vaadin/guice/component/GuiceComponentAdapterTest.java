package net.timeboxing.guice.vaadin.guice.component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.guice.vaadin.guice.component.impl.*;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.VaadinComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GuiceComponentAdapterTest {

    @Test
    void canAdapt() {
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        User user = injector.getInstance(UserFactory.class).create(5);
        Optional<VaadinComponent> component = user.adaptTo(VaadinComponent.class, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(UserViewComponent.class, component.orElseThrow().getClass());
    }

    @Test
    void adaptContainsPurpose() {
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        User user = injector.getInstance(UserFactory.class).create(5);
        Optional<VaadinComponent> component = user.adaptTo(VaadinComponent.class, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(ComponentPurpose.VIEW, ((UserViewComponent) component.orElseThrow()).purpose());
    }


    @Test
    void adaptContainsInjectedMembers() {
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        User user = injector.getInstance(UserFactory.class).create(5);
        Optional<VaadinComponent> component = user.adaptTo(VaadinComponent.class, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals("12345", ((UserViewComponent) component.orElseThrow()).testService().callMe());
    }

    @Test
    void differentPurposeSameClass() {
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        User user = injector.getInstance(UserFactory.class).create(5);
        Optional<VaadinComponent> component = user.adaptTo(VaadinComponent.class, ComponentPurpose.EDIT);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(UserEditComponent.class, component.orElseThrow().getClass());
    }
}
