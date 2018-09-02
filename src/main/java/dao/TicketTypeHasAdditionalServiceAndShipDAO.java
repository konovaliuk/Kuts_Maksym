package dao;

import java.util.HashMap;
import java.util.List;

public interface TicketTypeHasAdditionalServiceAndShipDAO {
    HashMap<Integer,List<Integer>> findListOfTicketTypesAndServicesByShipId(Long shipId);
    void deleteRowByShipIdAndTicketTypeId(Long shipId,Integer ticketTypeId);
    void addRow(Long shipId,Integer ticketTypeId,Integer serviceId);

}