package dao.impl;

import dao.ConnectionPool;
import dao.RouteHasPortDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteHasPortDAOImpl implements RouteHasPortDAO {
    private final static Logger logger = Logger.getLogger(RouteHasPortDAOImpl.class.getSimpleName());
    private static RouteHasPortDAOImpl instance;

    private RouteHasPortDAOImpl (){}
    @Override
    public List<Long> findAllPortsIdByRouteId(Long id) {
        List<Long> result = new ArrayList();
        String sql="SELECT * FROM route_has_port WHERE route_id = ? ORDER BY port_position";

        try (Connection connection = ConnectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getLong("port_id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
    public static RouteHasPortDAOImpl getInstance(){
        if(instance == null){
            instance = new RouteHasPortDAOImpl();
        }
        return instance;
    }
}
