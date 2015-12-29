package com.apukhtin.listeners; /**
 * Created by vlad on 19.12.2015.
 */

import com.apukhtin.dao.postgre.PostrgeUserDAO;
import com.apukhtin.services.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class LoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PostrgeUserDAO daoImpl = new PostrgeUserDAO();
        servletContextEvent.getServletContext().setAttribute("postgreDao", daoImpl);
        servletContextEvent.getServletContext().setAttribute("userService", new UserService(daoImpl));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
