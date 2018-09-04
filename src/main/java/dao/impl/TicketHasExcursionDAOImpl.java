package dao.impl;

import dao.ConnectionPool;
import dao.TicketHasExcursionDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketHasExcursionDAOImpl implements TicketHasExcursionDAO {
    private static final Logger logger =Logger.getLogger(TicketHasExcursionDAOImpl.class.getSimpleName());
    private static TicketHasExcursionDAOImpl instance;

    private TicketHasExcursionDAOImpl(){}

    @Override
    public void addRow(Long ticketId, Long excursionId) {
        String sql ="INSERT INTO ticket_has_excursion(ticket_id, excursion_id) VALUES (?,?)";
        try(Connection connection = ConnectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setLong(1,ticketId);
          statement.setLong(2,excursionId);
          statement.execute();
        } catch (SQLException e) {
           logger.error(e.getMessage());
        }
    }

    @Override
    public List<Long> findExcursionsIdByTicketId(Long ticketId) {
        List<Long> result = new ArrayList();
        String sql="SELECT * FROM ticket_has_excursion WHERE ticket_id = ?";

        try (Connection connection = ConnectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,ticketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getLong("excursion_id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static TicketHasExcursionDAOImpl getInstance(){
        if(instance == null){
            instance = new TicketHasExcursionDAOImpl();
        }
        return instance;
    }
}
