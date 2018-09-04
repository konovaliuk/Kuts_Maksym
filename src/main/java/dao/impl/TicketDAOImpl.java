package dao.impl;

import dao.TicketDAO;
import dao.util.DBUtil;
import enteties.Ticket;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private static TicketDAOImpl instance;

    private TicketDAOImpl () {}

    @Override
    public Ticket findTicketById(Long id) {
        String SQL="SELECT * FROM ticket WHERE ticket_id = ?";
        List<Ticket> tickets = findTicketsDynamically(SQL,id);
        System.out.println("List of ticket = "+tickets.toString());
        if(tickets.size()>0){
            return tickets.get(0);
        }
        return null;
    }

    @Override
    public List<Ticket> findTicketsByShipId(Long shipId) {
        String SQL = "SELECT * FROM ticket WHERE ship_id = ?";
        return findTicketsDynamically(SQL,shipId);
    }

    @Override
    public List<Ticket> findFreeTicketsByShipId(Long shipId) {
        String SQL = "SELECT * FROM ticket WHERE ship_id = ? AND booked = 0";
        return findTicketsDynamically(SQL,shipId);
    }

    @Override
    public List<Ticket> findAllTickets() {
        String SQL = "SELECT * FROM ticket";
        return findTicketsDynamically(SQL);
    }

    @Override
    public List<Ticket> findTicketsDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Ticket,sql,values);
    }

    @Override
    public void updateTicket(Long ticketId, Ticket ticket) {
        String SQL = "UPDATE ticket SET user_id = ?, booked = 1 WHERE ticket_id = ?";
        DBUtil.updateObjectDynamically(SQL,ticket.getUserId(),ticketId);
    }

    public static TicketDAOImpl getInstance(){
        if (instance == null){
            instance = new TicketDAOImpl();
        }
        return instance;
    }
}
