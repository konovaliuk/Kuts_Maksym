package dao.impl;

import dao.ConnectionPool;
import dao.TicketTypeHasAdditionalServiceAndShipDAO;
import dao.util.TransactionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketTypeHasAdditionalServiceAndShipDAOImpl implements TicketTypeHasAdditionalServiceAndShipDAO {
    private static final Logger logger = Logger.getLogger(TicketTypeHasAdditionalServiceAndShipDAOImpl.class.getSimpleName());
    private static TicketTypeHasAdditionalServiceAndShipDAOImpl instance;

    private TicketTypeHasAdditionalServiceAndShipDAOImpl (){}

    @Override
    public HashMap<Integer, List<Integer>> findListOfTicketTypesAndServicesByShipId(Long shipId) {
        HashMap<Integer, List<Integer>> result = new HashMap<>();
        String SQL = "SELECT ticket_type_id,additional_service_id FROM ticket_type_has_additional_service WHERE ship_id = ? ORDER BY ticket_type_id";
        try (Connection connection = ConnectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, shipId);
            ResultSet resultSet = statement.executeQuery();
            Integer previousType = -1;
            List<Integer> serviceHolder = new ArrayList<>();
            while (resultSet.next()) {
                if (previousType == -1) {
                    previousType = resultSet.getInt("ticket_type_id");
                }

                if (previousType != resultSet.getInt("ticket_type_id")) {
                    result.put(previousType, serviceHolder);
                    previousType = resultSet.getInt("ticket_type_id");
                    serviceHolder = new ArrayList<>();
                }

                serviceHolder.add(resultSet.getInt("additional_service_id"));
            }
            result.put(previousType, serviceHolder);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void deleteRowByShipIdAndTicketTypeId(Long shipId, Integer ticketTypeId) {
        String SQL = "DELETE FROM ticket_type_has_additional_service WHERE ship_id=? AND ticket_type_id = ?";
        try {

            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1, shipId);
            statement.setInt(2, ticketTypeId);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            TransactionManager.rollback();
        }
    }


    @Override
    public void addRow(Long shipId, Integer ticketTypeId, Integer serviceId) {
        String SQL = "INSERT INTO ticket_type_has_additional_service(ship_id,ticket_type_id,additional_service_id) VALUES (?,?,?)";
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1, shipId);
            statement.setInt(2, ticketTypeId);
            statement.setInt(3, serviceId);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            TransactionManager.rollback();
        }

    }

    public static TicketTypeHasAdditionalServiceAndShipDAOImpl getInstance(){
        if(instance == null){
            instance = new TicketTypeHasAdditionalServiceAndShipDAOImpl();
        }
        return instance;
    }
}
