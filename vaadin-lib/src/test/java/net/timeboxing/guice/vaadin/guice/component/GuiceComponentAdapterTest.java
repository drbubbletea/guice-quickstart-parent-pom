package net.timeboxing.guice.vaadin.guice.component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.guice.vaadin.guice.component.impl.DefaultUser;
import net.timeboxing.guice.vaadin.guice.component.impl.User;
import net.timeboxing.guice.vaadin.guice.component.impl.UserEditComponent;
import net.timeboxing.guice.vaadin.guice.component.impl.UserViewComponent;
import net.timeboxing.vaadin.component.ComponentAdapter;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.VaadinComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class GuiceComponentAdapterTest {

    @Test
    public void canAdapt() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        Optional<VaadinComponent> component = ComponentAdapter.adapt(user, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(UserViewComponent.class, component.orElseThrow().getClass());
    }

    @Test
    public void adaptContainsPurpose() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        Optional<VaadinComponent> component = ComponentAdapter.adapt(user, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(ComponentPurpose.VIEW, ((UserViewComponent) component.orElseThrow()).purpose());
    }


    @Test
    public void adaptContainsInjectedMembers() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        Optional<VaadinComponent> component = ComponentAdapter.adapt(user, ComponentPurpose.VIEW);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals("12345", ((UserViewComponent) component.orElseThrow()).testService().callMe());
    }

    @Test
    public void differentPurposeSameClass() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        Optional<VaadinComponent> component = ComponentAdapter.adapt(user, ComponentPurpose.EDIT);

        Assertions.assertTrue(component.isPresent());
        Assertions.assertEquals(UserEditComponent.class, component.orElseThrow().getClass());
    }
}
