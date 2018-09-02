package dao;


import java.util.List;

public interface RouteHasPortDAO {
    List<Long> findAllPortsIdByRouteId(Long id);
}
