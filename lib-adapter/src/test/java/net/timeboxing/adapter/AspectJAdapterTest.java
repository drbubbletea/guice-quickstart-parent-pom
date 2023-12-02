package net.timeboxing.adapter;

import com.google.inject.CreationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.adapter.guice.AspectJAdaptModule;
import net.timeboxing.adapter.impl.CustomPurpose;
import net.timeboxing.adapter.impl.DomainFactory;
import net.timeboxing.adapter.impl.FooBar;
import net.timeboxing.adapter.impl.TestAdapterModule;
import net.timeboxing.adapter.impl.User;
import net.timeboxing.adapter.impl.widget.CustomFooUserWidget;
import net.timeboxing.adapter.impl.widget.DefaultPurposeUserWidget;
import net.timeboxing.adapter.impl.widget.FooBarFooUserWidget;
import net.timeboxing.adapter.impl.widget.Widget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class AspectJAdapterTest {

    @Test
    @DisplayName("Can adapt with implied GENERAL purpose")
    public void canAdapt() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());

        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Foo");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertTrue(userWidget.isPresent());
        Assertions.assertTrue(userWidget.get().getClass().equals(DefaultPurposeUserWidget.class));
    }

    @Test
    @DisplayName("Missing Inject-annotated constructor throws helpful exception")
    public void adaptMissingInjectAnnotatedConstructorThrowsHelpfulException() {
        Exception exception = Assertions.assertThrows(CreationException.class, () -> {
            Guice.createInjector(new TestAdapterModule("net.timeboxing", "net.exceptions1"), new AspectJAdaptModule());
        });
        Throwable cause = exception.getCause();
        Assertions.assertEquals(AdaptException.class, cause.getClass());
        Assertions.assertEquals("No constructor annotated with javax.inject.Inject found in net.exceptions1.impl.widget.CustomErrorWidgetNoInjectConstructor", cause.getMessage());
    }
    @Test
    @DisplayName("Missing Adaptee-annotated constructor param throws helpful exception")
    public void adaptMissingAdapteeAnnotatedConstructorParamThrowsHelpfulException() {
        try {
            Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());

            DomainFactory factory = injector.getInstance(DomainFactory.class);
            User user = factory.create("Foo");
            user.adaptTo(Widget.class, CustomPurpose.ERROR2);
        } catch (AdaptException exception) {
            Assertions.assertEquals("Failed to create instance. Possibly forgot to annotate @Adaptee or @Purpose on the source constructor parameter?", exception.getMessage());
            return;
        }
        Assertions.fail("Expected an exception to be thrown");
    }

    @Test
    void emptyAdaptNothingInPackage() {
        Injector injector = Guice.createInjector(new TestAdapterModule("test.nothing.in.package"), new AspectJAdaptModule());

        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Foo");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertFalse(userWidget.isPresent());
    }

    @Test
    void canAdaptDefaultPurposeEnumDefaultPurposeValue() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Foo");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(DefaultPurposeUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Foo!!!", widget.display());
    }

    @Test
    void canAdaptCustomPurposeEnumFooPurposeValue() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Foo");
        Optional<Widget> userWidget = user.adaptTo(Widget.class, CustomPurpose.class, CustomPurpose.FOO);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(CustomFooUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Foo foo", widget.display());
    }
    @Test
    void canAdaptCustomAnnotationCustomPurpose() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Foo");
        Optional<Widget> userWidget = user.adaptTo(Widget.class, FooBar.class, FooBar.FOO);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(FooBarFooUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Foo FooBar default foo", widget.display());
    }
}
