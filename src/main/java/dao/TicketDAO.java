package dao;


import enteties.Ticket;

import java.util.List;

public interface TicketDAO {

    Ticket findTicketById(Long id);
    List<Ticket> findTicketsByShipId(Long shipId);
    List<Ticket> findFreeTicketsByShipId(Long shipId);
    List<Ticket> findAllTickets();
    List<Ticket> findTicketsDynamically(String sql, Object... values);
    void updateTicket(Long ticketId,Ticket ticket);

}
