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
        if(tickets.size()>0){
            tickets.get(0);
        }
        return null;
    }

    @Override
    public List<Ticket> findTicketsByShipId(Long shipId) {
        String SQL = "SELECT * FROM ticket WHERE ship_id = ?";
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

    public static TicketDAOImpl getInstance(){
        if (instance == null){
            instance = new TicketDAOImpl();
        }
        return instance;
    }
}
