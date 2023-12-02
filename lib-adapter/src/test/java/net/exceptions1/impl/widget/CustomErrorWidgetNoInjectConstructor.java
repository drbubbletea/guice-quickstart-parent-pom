package net.exceptions1.impl.widget;

import net.timeboxing.adapter.AdaptedFrom;
import net.timeboxing.adapter.Adaptee;
import net.timeboxing.adapter.impl.CustomPurpose;
import net.timeboxing.adapter.impl.User;
import net.timeboxing.adapter.impl.widget.Widget;

@AdaptedFrom(from = User.class, to = Widget.class, purposeEnum = CustomPurpose.class, purposeValue = "ERROR")
public class CustomErrorWidgetNoInjectConstructor implements Widget {

    private final User user;
//    @Inject
    public CustomErrorWidgetNoInjectConstructor(@Adaptee User user) {
        this.user = user;
    }

    @Override
    public String display() {
        return user.name() + "error!";
    }
}
