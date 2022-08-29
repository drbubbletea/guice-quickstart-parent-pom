package net.timeboxing.webapp;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route("")
public class ApplicationLayout extends AppLayout {

    @Inject
    public ApplicationLayout(MainView mainView) {
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Webapp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l") //
                        .set("margin", "0");
//        addToDrawer();
        addToNavbar(toggle, title);
        setContent(mainView);
    }
}
