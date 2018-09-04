package dao.util;

import dao.ConnectionPool;
import enteties.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final Logger logger = Logger.getLogger(DBUtil.class.getSimpleName());

    public static void updateObjectDynamically(String sql, Object... values) {

        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; values != null && i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            if (connection != null) {
                TransactionManager.rollback();
            }

        }
    }

    public static <T> List<T> findObjectDynamically(ObjectType objectType ,String sql,Object... values){
        List<T> result = new ArrayList();

        try(Connection connection = ConnectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i=0; values!= null && i<values.length;i++) {
                statement.setObject(i+1,values[i]);
            }
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
               switch (objectType){
                   case AdditionalService:
                       result.add((T) getServiceFromResultSet(resultSet));
                       break;
                   case Employer:
                       result.add((T) getEmployerFromResultSet(resultSet));
                       break;
                   case Excursion:
                       result.add((T) getExcursionFromResultSet(resultSet));
                       break;
                   case Order:
                       result.add((T) getOrderFromResultSet(resultSet));
                       break;
                   case Port:
                       result.add((T) getPortFromResultSet(resultSet));
                       break;
                   case Route:
                       result.add((T) getRouteFromResultSet(resultSet));
                       break;
                   case Ship:
                       result.add((T) getShipFromResultSet(resultSet));
                       break;
                   case Ticket:
                       result.add((T) getTicketFromResultSet(resultSet));
                       break;
                   case TicketType:
                       result.add((T) getTicketTypeFromResultSet(resultSet));
                       break;
                   case User:
                       result.add((T) getUserFromResultSet(resultSet));
                       break;
               }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;

    }

    private static AdditionalService getServiceFromResultSet(ResultSet resultSet) throws SQLException {
        AdditionalService service = new AdditionalService();
        service.setId(resultSet.getInt("additional_service_id"));
        service.setTitle(resultSet.getString("additional_service_title"));
        return service;
    }
    private static Employer getEmployerFromResultSet(ResultSet resultSet) throws SQLException {
        Employer employer = new Employer();
        employer.setId(resultSet.getInt("employer_id"));
        employer.setFirstName(resultSet.getString("employer_firstname"));
        employer.setLastName(resultSet.getString("employer_lastname"));
        employer.setShipId(resultSet.getLong("ship_id"));
        employer.setPosition(resultSet.getString("employer_position"));
        return employer;
    }
    private static Excursion getExcursionFromResultSet(ResultSet resultSet) throws SQLException {
        Excursion excursion = new Excursion();
        excursion.setId(resultSet.getLong("excursion_id"));
        excursion.setTitle(resultSet.getString("excursion_title"));
        excursion.setDescription(resultSet.getString("excursion_description"));
        excursion.setPrice(resultSet.getBigDecimal("excursion_price"));
        excursion.setPortId(resultSet.getLong("port_id"));
        return excursion;
    }
    private static Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        order.setPaid(resultSet.getInt("paid") == 0?false:true);
        order.setPurchaseDate(resultSet.getDate("purchase_date"));
        return order;
    }
    private static Port getPortFromResultSet(ResultSet resultSet) throws SQLException {
        Port port = new Port();
        port.setId(resultSet.getLong("port_id"));
        port.setTitle(resultSet.getString("port_title"));
        return port;
    }
    private static Route getRouteFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getLong("route_id"));
        route.setTitle(resultSet.getString("route_title"));
        return route;
    }
    private static Ship getShipFromResultSet(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getLong("ship_id"));
        ship.setHumanCapacity(resultSet.getInt("human_capacity"));
        ship.setStartDate(resultSet.getTimestamp("start_date"));
        ship.setEndDate(resultSet.getTimestamp("end_date"));
        ship.setRouteId(resultSet.getLong("route_id"));
        ship.setTitle(resultSet.getString("ship_title"));
        ship.setPrice(resultSet.getBigDecimal("cruise_price"));
        return ship;
    }
    private static Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("ticket_id"));
        ticket.setTicketTypeId(resultSet.getInt("ticket_type_id"));
        ticket.setShipId(resultSet.getLong("ship_id"));
        ticket.setUserId(resultSet.getLong("user_id"));
        ticket.setBooked(resultSet.getInt("booked") == 0?false:true);
        return ticket;
    }
    private static TicketType getTicketTypeFromResultSet(ResultSet resultSet) throws SQLException {
        TicketType ticketType = new TicketType();
        ticketType.setId(resultSet.getInt("ticket_type_id"));
        ticketType.setTicketType(resultSet.getString("ticket_category"));
        ticketType.setPrice(resultSet.getBigDecimal("price"));
        return ticketType;
    }
    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setUserRole(resultSet.getString("user_role"));
        return user;
    }

    public enum ObjectType{
        AdditionalService,
        Employer,
        Excursion,
        Order,
        Port,
        Route,
        Ship,
        Ticket,
        TicketType,
        User
    }
}
