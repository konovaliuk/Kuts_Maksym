package dao.impl;

import dao.PortDAO;
import dao.util.DBUtil;
import enteties.Port;

import java.util.List;

public class PortDAOImpl implements PortDAO {
    private static PortDAOImpl instance;

    private PortDAOImpl(){}

    @Override
    public Port findPortById(Long id) {
        String SQL ="SELECT * FROM port WHERE port_id = ?";
        List<Port> ports = findPortsDynamically(SQL,id);
        if(ports.size()>0){
            return ports.get(0);
        }
        return null;
    }

    @Override
    public List<Port> findAllPorts() {
        String SQL ="SELECT * FROM port";
        return findPortsDynamically(SQL);
    }

    @Override
    public List<Port> findPortsDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Port,sql,values);
    }
    public static PortDAOImpl getInstance(){
        if(instance == null){
            instance = new PortDAOImpl();
        }
        return instance;
    }
}
