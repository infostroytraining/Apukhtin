package com.apukhtin.servlets;

import com.apukhtin.dao.memory.InMemoryUserDaoImpl;
import com.apukhtin.services.UserService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

/**
 * Created by vlad on 26.12.2015.
 */
public class RegisterServletTest {
    RegisterServlet servlet = mock(RegisterServlet.class);

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletContext context;
    private UserService service;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        context = mock(ServletContext.class);
        service = new UserService(new InMemoryUserDaoImpl());
    }

    @Test
    public void testDoPostValid() throws Exception {
        when(context.getAttribute("userService")).thenReturn(service);
        when(request.getParameter("email")).thenReturn("apfhk@nfilj.ru");
        when(request.getParameter("name")).thenReturn("vlad");
        when(request.getParameter("password")).thenReturn("iufhwiu");
    }

    @Test
    public void testForNullValidation() throws Exception {
        when(context.getAttribute("userService")).thenReturn(service);
//        when(response.get)
        verify(response.getWriter()).append(anyString());
    }

    @Test
    public void testForNullResponseAppend() throws Exception {

    }
}