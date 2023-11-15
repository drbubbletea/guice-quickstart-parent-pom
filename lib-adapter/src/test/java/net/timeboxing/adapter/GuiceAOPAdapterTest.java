package net.timeboxing.adapter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.adapter.guice.GuiceAOPAdaptModule;
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

import java.util.Optional;

class GuiceAOPAdapterTest {

//    @Test
    void emptyAdaptNothingInPackage() {
        Injector injector = Guice.createInjector(new TestAdapterModule("test.nothing.in.package"), new GuiceAOPAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Crazy Bob");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertFalse(userWidget.isPresent());
    }

//    @Test
    void canAdaptDefaultPurposeEnumDefaultPurposeValue() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing.adapter"), new GuiceAOPAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Crazy Bob");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(DefaultPurposeUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Crazy Bob!!!", widget.display());
    }

//    @Test
    void canAdaptCustomPurposeEnumFooPurposeValue() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing.adapter"), new GuiceAOPAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Crazy Bob");
        Optional<Widget> userWidget = user.adaptTo(Widget.class, CustomPurpose.class, CustomPurpose.FOO);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(CustomFooUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Crazy Bob foo", widget.display());
    }
//    @Test
    void canAdaptCustomAnnotationCustomPurpose() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing.adapter"), new GuiceAOPAdaptModule());
        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Crazy Bob");
        Optional<Widget> userWidget = user.adaptTo(Widget.class, FooBar.class, FooBar.FOO);
        Assertions.assertTrue(userWidget.get().getClass().isAssignableFrom(FooBarFooUserWidget.class));
        Widget widget = userWidget.get();
        Assertions.assertEquals("Crazy Bob FooBar default foo", widget.display());
    }
}
