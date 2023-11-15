package net.timeboxing.adapter.impl.widget;

import net.timeboxing.adapter.Adaptee;
import net.timeboxing.adapter.impl.FooBarFor;
import net.timeboxing.adapter.impl.User;

import javax.inject.Inject;

@FooBarFor(from = User.class, to = Widget.class)
public class FooBarFooUserWidget implements Widget {

    private final User user;
    @Inject
    public FooBarFooUserWidget(@Adaptee User user) {
        this.user = user;
    }
    @Override
    public String display() {
        return user.name() + " FooBar default foo";
    }
}
