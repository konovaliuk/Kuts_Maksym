package dao.impl;

import dao.ShipDAO;
import dao.util.DBUtil;
import enteties.Ship;

import java.util.List;

public class ShipDAOImpl implements ShipDAO {
    private static ShipDAOImpl instance;

    private ShipDAOImpl(){}

    @Override
    public Ship findShipById(Long id) {
        String SQL = "SELECT * FROM ship WHERE ship_id = ?";
        List<Ship> ships = findShipsDynamically(SQL,id);
        if(ships.size()>0){
            return ships.get(0);
        }
        return null;
    }

    @Override
    public List<Ship> findAllShips() {
        String SQL = "SELECT * FROM ship";
        return findShipsDynamically(SQL);
    }

    @Override
    public List<Ship> findShipsDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Ship,sql,values);
    }

    public static ShipDAOImpl getInstance(){
        if(instance == null){
            instance = new ShipDAOImpl();
        }
        return instance;
    }
}
