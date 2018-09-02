package dao.impl;

import dao.RouteDAO;
import dao.util.DBUtil;
import enteties.Route;

import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    @Override
    public Route findRouteById(Long id) {
        String SQL = "SELECT * FROM route WHERE route_id = ?";
        List<Route> routes = findRoutesDynamically(SQL,id);
        if(routes.size()>0){
            return routes.get(0);
        }
        return null;
    }

    @Override
    public List<Route> findAllRoutes() {
        String SQL = "SELECT * FROM route";
        return findRoutesDynamically(SQL);
    }

    @Override
    public List<Route> findRoutesDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Route,sql,values);
    }
}
