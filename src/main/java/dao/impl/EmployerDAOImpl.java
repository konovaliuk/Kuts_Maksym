package dao.impl;

import dao.EmployerDAO;
import dao.util.DBUtil;
import enteties.Employer;

import java.util.List;

public class EmployerDAOImpl implements EmployerDAO {
    @Override
    public Employer findEmployerById(Integer id) {
        String SQL = "SELECT * FROM employer WHERE employer_id = ?";
        List<Employer> employers = findEmployersDynamically(SQL,id);
        if(employers.size()>0){
            return employers.get(0);
        }
        return null;
    }

    @Override
    public List<Employer> findAllEmployers() {
        String SQL = "SELECT * FROM employer";
        return findEmployersDynamically(SQL);
    }

    @Override
    public List<Employer> findEmployersDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Employer,sql,values);
    }
}
