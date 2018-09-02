package dao;

import enteties.AdditionalService;

import java.util.List;

public interface AdditionalServiceDAO {

    AdditionalService findServiceById(Integer id);
    List<AdditionalService> findAllService();
    List<AdditionalService> findAdditionalServiceDynamically(String sql, Object... values);
}
