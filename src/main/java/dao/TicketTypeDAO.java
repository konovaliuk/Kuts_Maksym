package dao;


import enteties.TicketType;

import java.util.List;

public interface TicketTypeDAO {

    TicketType findTicketTypeById(Integer id);
    List<TicketType> findAllTicketTypes();
    List<TicketType> findTicketTypesDynamically(String sql, Object... values);

}
