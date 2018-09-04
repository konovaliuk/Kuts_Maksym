package commands;

import enteties.AdditionalService;
import enteties.Ship;
import enteties.Ticket;
import enteties.TicketType;
import service.ShipPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


public class ShipPageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long shipId = Long.parseLong(request.getParameter("shipId"));
        Ship ship = ShipPageService.getShipById(shipId);
        if(ship == null ){
            return "index.jsp";
        }

        HashMap<TicketType,Integer> ticketTypeCount = new HashMap<>();
        HashMap<Integer,List<AdditionalService>> ticketTypeAdditionalServices = new HashMap<>();

        for(TicketType type : ShipPageService.getAllTicketTypes()){
            List<Ticket> tickets = ShipPageService.getFreeTicketsByTicketTypeId(shipId,type.getId());
            List<AdditionalService> services = ShipPageService.getAdditionalServices(shipId,type.getId());

            if(tickets != null){
                ticketTypeCount.put(type,tickets.size());
            }
            if(services != null){
                ticketTypeAdditionalServices.put(type.getId(),services);
            }
        }

        request.setAttribute("excursionList",ShipPageService.getExcursions(shipId));
        request.setAttribute("ticketTypeAdditionalServices",ticketTypeAdditionalServices);
        request.setAttribute("ticketTypeCount",ticketTypeCount);
        request.setAttribute("ship",ship);
        return "shipPage.jsp";

    }
}
