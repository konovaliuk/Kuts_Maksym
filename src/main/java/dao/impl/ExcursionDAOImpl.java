package dao.impl;

import dao.ExcursionDAO;
import dao.util.DBUtil;
import enteties.Excursion;

import java.util.List;

public class ExcursionDAOImpl implements ExcursionDAO {
    private static ExcursionDAOImpl instance;

    private ExcursionDAOImpl(){}

    @Override
    public Excursion findExcursionById(Long id) {
        String SQL = "SELECT * FROM excursion WHERE excursion_id = ?";
        List<Excursion> excursions = findExcursionsDynamically(SQL,id);
        if(excursions.size()>0){
            return excursions.get(0);
        }
        return null;
    }

    @Override
    public List<Excursion> findAllExcursions() {
        String SQL = "SELECT * FROM excursion";
        return findExcursionsDynamically(SQL);
    }

    @Override
    public List<Excursion> findExcursionsByPortId(Long portId) {
        String SQL = "SELECT * FROM excursion WHERE port_id = ?";
        return findExcursionsDynamically(SQL,portId);
    }

    @Override
    public List<Excursion> findExcursionsDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Excursion,sql,values);
    }

    public static ExcursionDAOImpl getInstance(){
        if(instance == null){
            instance = new ExcursionDAOImpl();
        }
        return instance;
    }
}
