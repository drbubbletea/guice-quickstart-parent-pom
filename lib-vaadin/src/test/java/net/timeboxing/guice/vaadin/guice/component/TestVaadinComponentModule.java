package net.timeboxing.guice.vaadin.guice.component;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import net.timeboxing.adapter.guice.AdapterModule;
import net.timeboxing.adapter.guice.GuiceAOPAdaptModule;
import net.timeboxing.guice.vaadin.guice.component.impl.DefaultTestService;
import net.timeboxing.guice.vaadin.guice.component.impl.DefaultVaadinUser;
import net.timeboxing.guice.vaadin.guice.component.impl.TestService;
import net.timeboxing.guice.vaadin.guice.component.impl.User;
import net.timeboxing.guice.vaadin.guice.component.impl.UserFactory;

public class TestVaadinComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        install(new AdapterModule("net.timeboxing"));
        install(new GuiceAOPAdaptModule());
//        install(new VaadinComponentModule("net.timeboxing"));
        bind(TestService.class).to(DefaultTestService.class).in(Scopes.SINGLETON);
        install(new FactoryModuleBuilder().implement(User.class, DefaultVaadinUser.class).build(UserFactory.class));
    }
}
