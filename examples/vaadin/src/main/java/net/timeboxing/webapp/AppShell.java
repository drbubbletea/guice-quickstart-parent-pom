package net.timeboxing.webapp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */

@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
public class AppShell implements AppShellConfigurator {
}
