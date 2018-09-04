package commands;

import enteties.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.ShipPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ShipPageService.class})
public class ShipPageCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    public void TestShipPageCommandExecute(){
        when(request.getParameter("shipId")).thenReturn("1");
        PowerMockito.mockStatic(ShipPageService.class);
        when(ShipPageService.getShipById(1l)).thenReturn(new Ship());

        List<TicketType> ticketTypes = new ArrayList<>();
        TicketType type = new TicketType();
        type.setId(1);
        type.setTicketType("simple");
        ticketTypes.add(type);

        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setId(1l);
        ticket.setUserId(null);
        ticket.setShipId(1l);
        ticket.setTicketTypeId(1);
        ticket.setBooked(false);
        tickets.add(ticket);

        List<AdditionalService> services = new ArrayList<>();
        AdditionalService service = new AdditionalService();
        service.setId(1);
        service.setTitle("service");
        services.add(service);

        HashMap<TicketType,Integer> ticketTypeCount = new HashMap<>();
        HashMap<Integer,List<AdditionalService>> ticketTypeAdditionalServices = new HashMap<>();
        ticketTypeCount.put(type,1);
        ticketTypeAdditionalServices.put(1,services);

        when(ShipPageService.getAllTicketTypes()).thenReturn(ticketTypes);
        when(ShipPageService.getFreeTicketsByTicketTypeId(1l,1)).thenReturn(tickets);
        when(ShipPageService.getAdditionalServices(1l,1)).thenReturn(services);
        when(ShipPageService.getExcursions(1l)).thenReturn(new ArrayList<>());

        String result = new ShipPageCommand().execute(request,response);

        verify(request,times(1)).setAttribute("excursionList",new ArrayList<>());
        verify(request,times(1)).setAttribute("ticketTypeAdditionalServices",ticketTypeAdditionalServices);
        verify(request,times(1)).setAttribute("ticketTypeCount",ticketTypeCount);
        verify(request,times(1)).setAttribute("ship",new Ship());
        assertEquals("shipPage.jsp",result);
    }
    @Test
    public void TestShipPageCommandExecuteWhereShipIsNull(){
        when(request.getParameter("shipId")).thenReturn("10");
        PowerMockito.mockStatic(ShipPageService.class);
        when(ShipPageService.getShipById(10l)).thenReturn(null);

        String result = new ShipPageCommand().execute(request,response);
        assertEquals("index.jsp",result);
    }

}