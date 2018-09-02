package service;

import dao.*;
import enteties.AdditionalService;
import enteties.Ship;
import enteties.TicketType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPageService {
    public static List<Ship> getAllShips(){
        ShipDAO shipDAO = DataBaseFactory.getShipDAO();
        return shipDAO.findAllShips();
    }
    public static List<AdditionalService> getAllServices(){
        AdditionalServiceDAO serviceDAO = DataBaseFactory.getServiceDAO();
        return serviceDAO.findAllService();
    }
    public static List<TicketType> getAllTicketTypes(){
        TicketTypeDAO ticketTypeDAO = DataBaseFactory.getTicketTypeDAO();
        return ticketTypeDAO.findAllTicketTypes();
    }
    public static HashMap<Integer,List<Integer>> getTypeServiceDependencyDyShipId(Long id){
        TicketTypeHasAdditionalServiceAndShipDAO dao = DataBaseFactory.getTypeServiceDAO();
        return dao.findListOfTicketTypesAndServicesByShipId(id);
    }
    public static void updateTypeServiceDependency(HashMap<Integer,List<Integer>> map, Long ship_id){
        TicketTypeHasAdditionalServiceAndShipDAO dao = DataBaseFactory.getTypeServiceDAO();
        for(Map.Entry<Integer,List<Integer>> pair:map.entrySet()){
            dao.deleteRowByShipIdAndTicketTypeId(ship_id,pair.getKey());
        }
        for(Map.Entry<Integer,List<Integer>> pair:map.entrySet()){
            List<Integer> services = pair.getValue();
                for(int i=0;i<services.size();i++) {
                    dao.addRow(ship_id, pair.getKey(),Integer.parseInt(String.valueOf(services.get(i))));
                }

        }
    }
}
