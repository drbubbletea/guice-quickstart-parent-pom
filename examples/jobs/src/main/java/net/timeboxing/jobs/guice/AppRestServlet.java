package net.timeboxing.jobs.guice;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

public class AppRestServlet extends HttpServletDispatcher {

    private static final Logger LOG = LoggerFactory.getLogger(AppRestServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        LOG.info("Init");
        super.init(servletConfig);
    }

}