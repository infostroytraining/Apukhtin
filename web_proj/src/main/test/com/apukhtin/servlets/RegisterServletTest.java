package com.apukhtin.servlets;

import com.apukhtin.dao.postgre.PostrgeUserDAO;
import com.apukhtin.model.User;
import com.apukhtin.services.ServiceException;
import com.apukhtin.services.UserService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

/**
 * Created by vlad on 26.12.2015.
 */
public class RegisterServletTest {
    RegisterServlet servlet = new RegisterServlet();

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletContext context;
    private UserService service;

    private void initUser() {
        when(request.getParameter("email")).thenReturn("apfhk@nfilj.ru");
        when(request.getParameter("name")).thenReturn("vlad");
        when(request.getParameter("password")).thenReturn("iufhwiu");
    }

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        context = mock(ServletContext.class);
        service = mock(UserService.class);

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("userService")).thenReturn(service);
        when(context.getAttribute("postgreDao")).thenReturn(mock(PostrgeUserDAO.class));
    }

    @Test
    public void testDoPostValid() throws Exception {
        initUser();
        servlet.doPost(request, response);
        verify(service).addUser(any(User.class));
    }

    @Test
    public void testForNullValidation() throws Exception {
        PrintWriter writer = (mock(PrintWriter.class));
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        verify(response.getWriter(), atLeastOnce()).append(anyString());
    }

    @Test
    public void testForException() throws Exception {
        initUser();
        when(response.getWriter()).thenReturn(mock(PrintWriter.class));
        doThrow(new ServiceException()).when(service).addUser(any(User.class));

        servlet.doPost(request, response);
        verify(response.getWriter()).append(anyString());
    }
}