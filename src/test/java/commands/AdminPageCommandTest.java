package commands;


import enteties.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AdminPageService;

import static org.mockito.Mockito.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdminPageService.class)

public class AdminPageCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);


    @Before
    public void setUp(){

        PowerMockito.mockStatic(AdminPageService.class);
        when(request.getSession()).thenReturn(session);

        when(AdminPageService.getAllShips()).thenReturn(new ArrayList<>());



        when(AdminPageService.getAllShips()).thenReturn(new ArrayList<>());
        when(AdminPageService.getAllTicketTypes()).thenReturn(new ArrayList<>());
        when(AdminPageService.getAllServices()).thenReturn(new ArrayList<>());
    }

    @Test
    public void whenAdminPageCommandExecuteHaveNoUser() {

        when(request.getSession().getAttribute("user")).thenReturn(null);

        String result = new AdminPageCommand().execute(request, response);


        verify(session, times(1)).getAttribute("user");

        verify(request, never()).setAttribute("ships", AdminPageService.getAllShips());
        verify(request,never()).setAttribute("ticketTypes",AdminPageService.getAllTicketTypes());
        verify(request,never()).setAttribute("services", AdminPageService.getAllServices());

        assertEquals("index.jsp", result);

    }
    @Test
    public void whenAdminPageCommandExecuteAdminUser(){
        User user = new User();
        user.setUserRole("admin");
        when(session.getAttribute("user")).thenReturn(user);

        String result = new AdminPageCommand().execute(request, response);


        verify(request, times(1)).getSession();
        verify(session, times(1)).getAttribute("user");

        verify(request, times(1)).setAttribute("ships", AdminPageService.getAllShips());
        verify(request,times(1)).setAttribute("ticketTypes",AdminPageService.getAllTicketTypes());
        verify(request,times(1)).setAttribute("services", AdminPageService.getAllServices());

        assertEquals("adminpanel.jsp", result);

    }
    @Test
    public void whenAdminPageCommandExecuteSimpleUser(){
        User user = new User();
        user.setUserRole("user");
        when(session.getAttribute("user")).thenReturn(user);

        String result = new AdminPageCommand().execute(request, response);


        verify(request, times(1)).getSession();
        verify(session, times(1)).getAttribute("user");

        verify(request, never()).setAttribute("ships", AdminPageService.getAllShips());
        verify(request,never()).setAttribute("ticketTypes",AdminPageService.getAllTicketTypes());
        verify(request,never()).setAttribute("services", AdminPageService.getAllServices());

        assertEquals("index.jsp", result);

    }

}