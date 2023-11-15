package net.timeboxing.adapter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.adapter.guice.AspectJAdaptModule;
import net.timeboxing.adapter.impl.DomainFactory;
import net.timeboxing.adapter.impl.TestAdapterModule;
import net.timeboxing.adapter.impl.User;
import net.timeboxing.adapter.impl.widget.DefaultPurposeUserWidget;
import net.timeboxing.adapter.impl.widget.Widget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class AspectJAdapterTest {

    @Test
    public void testAspectjAdapt() {
        Injector injector = Guice.createInjector(new TestAdapterModule("net.timeboxing"), new AspectJAdaptModule());

        DomainFactory factory = injector.getInstance(DomainFactory.class);
        User user = factory.create("Crazy Bob");
        Optional<Widget> userWidget = user.adaptTo(Widget.class);
        Assertions.assertTrue(userWidget.isPresent());
        Assertions.assertTrue(userWidget.get().getClass().equals(DefaultPurposeUserWidget.class));
    }
}
