package dao;


import enteties.Employer;

import java.util.List;

public interface EmployerDAO {

    Employer findEmployerById(Integer id);
    List<Employer> findAllEmployers();
    List<Employer> findEmployersDynamically(String sql, Object... values);
}
