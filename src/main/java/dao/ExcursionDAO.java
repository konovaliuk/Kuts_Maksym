package dao;


import enteties.Excursion;

import java.util.List;

public interface ExcursionDAO {

    Excursion findExcursionById(Long id);
    List<Excursion> findAllExcursions();
    List<Excursion> findExcursionsByPortId(Long portId);
    List<Excursion> findExcursionsDynamically(String sql, Object... values);
}
