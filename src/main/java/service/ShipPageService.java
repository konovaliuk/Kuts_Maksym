package service;

import dao.*;
import enteties.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ShipPageService {
    public static Ship getShipById(Long id) {
        ShipDAO shipDAO = DataBaseFactory.getShipDAO();
        return shipDAO.findShipById(id);
    }

    public static List<Ticket> getFreeTicketsByTicketTypeId(Long shipId, Integer typeId) {
        List<Ticket> result = new ArrayList<>();
        TicketDAO ticketDAO = DataBaseFactory.getTicketDAO();
        List<Ticket> allTickets = ticketDAO.findTicketsByShipId(shipId);
        for (Ticket item : allTickets) {
            if (item.getTicketTypeId() == typeId) {
                result.add(item);
            }
        }
        return result;
    }

    public static List<TicketType> getAllTicketTypes() {
        TicketTypeDAO typeDAO = DataBaseFactory.getTicketTypeDAO();
        return typeDAO.findAllTicketTypes();
    }

    public static List<AdditionalService> getAdditionalServices(Long shipId, Integer typeId) {
        List<AdditionalService> result = new ArrayList<>();

        TicketTypeHasAdditionalServiceAndShipDAO dao = DataBaseFactory.getTypeServiceDAO();
        AdditionalServiceDAO serviceDAO = DataBaseFactory.getServiceDAO();
        HashMap<Integer, List<Integer>> typeService = dao.findListOfTicketTypesAndServicesByShipId(shipId);
        if (typeService.containsKey(typeId)) {
            List<AdditionalService> services = new ArrayList<>();
            for (Integer i : typeService.get(typeId)) {
                services.add(serviceDAO.findServiceById(i));
            }
            result = services;
        }
        return result;
    }

    public static List<Excursion> getExcursions(Long shipId) {
        List<Excursion> result = new ArrayList<>();
        ShipDAO shipDAO = DataBaseFactory.getShipDAO();
        ExcursionDAO excursionDAO = DataBaseFactory.getExcursionDAO();
        RouteHasPortDAO routeHasPortDAO = DataBaseFactory.getRouteHasPortDAO();
        Ship ship = shipDAO.findShipById(shipId);
        for(Long i :new HashSet<>(routeHasPortDAO.findAllPortsIdByRouteId(ship.getRouteId()))){
            for(Excursion e:excursionDAO.findExcursionsByPortId(i)){
                result.add(e);
            }
        }
        return result;
    }
}
