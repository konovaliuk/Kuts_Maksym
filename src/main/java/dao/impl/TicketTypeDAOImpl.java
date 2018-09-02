package dao.impl;

import dao.TicketTypeDAO;
import dao.util.DBUtil;
import enteties.TicketType;

import java.util.List;

public class TicketTypeDAOImpl implements TicketTypeDAO {
    private static TicketTypeDAOImpl instance;

    private TicketTypeDAOImpl (){}

    @Override
    public TicketType findTicketTypeById(Integer id) {
        String SQL = "SELECT * FROM ticket_type WHERE ticket_type_id = ?";
        List<TicketType> ticketTypes = findTicketTypesDynamically(SQL,id);
        if(ticketTypes.size()>0){
            return ticketTypes.get(0);
        }
        return null;
    }

    @Override
    public List<TicketType> findAllTicketTypes() {
        String SQL ="SELECT * FROM ticket_type";
        return findTicketTypesDynamically(SQL);
    }

    @Override
    public List<TicketType> findTicketTypesDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.TicketType,sql,values);
    }

    public static TicketTypeDAOImpl getInstance(){
        if(instance == null){
            instance = new TicketTypeDAOImpl();
        }
        return instance;
    }

}
