package dao;


import enteties.Ship;

import java.util.List;

public interface ShipDAO {

    Ship findShipById(Long id);
    List<Ship> findAllShips();
    List<Ship> findShipsDynamically(String sql, Object... values);

}
