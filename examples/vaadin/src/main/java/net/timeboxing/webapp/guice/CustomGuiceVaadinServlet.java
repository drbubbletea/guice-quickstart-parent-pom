package net.timeboxing.webapp.guice;

import com.vaadin.guice.annotation.PackagesToScan;
import com.vaadin.guice.server.GuiceVaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", asyncSupported = true, name = "webapp")
@PackagesToScan("net.timeboxing.webapp")
public class CustomGuiceVaadinServlet extends GuiceVaadinServlet {
}
