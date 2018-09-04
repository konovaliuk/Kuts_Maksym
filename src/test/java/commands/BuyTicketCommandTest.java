package commands;

import enteties.Ship;
import enteties.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AdminPageService;
import service.ShipPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ShipPageService.class)
public class BuyTicketCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);
    final PrintWriter writer = mock(PrintWriter.class);
    final User user = new User();

    @Before
    public void setUp() throws IOException {
        user.setId(2l);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        when(request.getParameter("shipId")).thenReturn("1");

        when(request.getParameter("ticketTypeId")).thenReturn("1");
        when(request.getParameter("count")).thenReturn("0");
        when(request.getParameter("excursionArray")).thenReturn("");
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void TestExecute(){

        PowerMockito.mockStatic(ShipPageService.class);
        when(ShipPageService.BuyTickets(1l,1,0,new ArrayList<Long>(),user.getId())).thenReturn("success");

        doNothing().when(writer).print(anyString());


        String result = new BuyTicketCommand().execute(request,response);



        assertEquals("ajax",result);
    }
    @Test
    public void TestExecuteWhenThrowException(){

        PowerMockito.mockStatic(ShipPageService.class);
        when(ShipPageService.BuyTickets(1l,1,0,new ArrayList<Long>(),user.getId())).thenReturn("error");
        doNothing().when(response).setStatus(anyInt());

        doThrow(IOException.class).when(writer).print(anyString());


        String result = new BuyTicketCommand().execute(request,response);



        assertEquals("ajax",result);
    }
}