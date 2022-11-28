package net.timeboxing.jobs.guice;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*")
public class AppRestServlet extends HttpServletDispatcher {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

}