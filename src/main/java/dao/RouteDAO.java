package dao;


import enteties.Route;

import java.util.List;

public interface RouteDAO {

    Route findRouteById(Long id);
    List<Route> findAllRoutes();
    List<Route> findRoutesDynamically(String sql, Object... values);

}
