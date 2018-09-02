package dao;

import java.util.List;

public interface TicketHasExcursionDAO {
    void addRow(Long ticketId,Long excursionId);
    List<Long> findExcursionsIdByTicketId(Long ticketId);
}
