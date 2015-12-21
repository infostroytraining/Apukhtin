package com.apukhtin; /**
 * Created by vlad on 21.12.2015.
 */

import com.apukhtin.dao.postgre.PostgreLogDAO;
import com.apukhtin.services.LogService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class LoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("logService", new LogService(new PostgreLogDAO()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
