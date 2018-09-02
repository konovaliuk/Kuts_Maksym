package dao.impl;

import dao.AdditionalServiceDAO;
import dao.util.DBUtil;
import enteties.AdditionalService;
import java.util.List;

public class AdditionalServiceDAOImpl implements AdditionalServiceDAO {
    private static AdditionalServiceDAOImpl instance;

    private AdditionalServiceDAOImpl (){}

    @Override
    public AdditionalService findServiceById(Integer id) {
        String SQL = "SELECT * FROM additional_service WHERE additional_service_id = ?";
        List<AdditionalService> services = findAdditionalServiceDynamically(SQL,id);
        if (services.size() > 0){
            return services.get(0);
        }
        return null;
    }

    @Override
    public List<AdditionalService> findAllService() {
        String SQL = "SELECT * FROM additional_service";
        return findAdditionalServiceDynamically(SQL);
    }

    @Override
    public List<AdditionalService> findAdditionalServiceDynamically(String sql, Object... values) {
        List<AdditionalService> result = DBUtil.findObjectDynamically(DBUtil.ObjectType.AdditionalService,sql,values);
       return result;
    }

    public static AdditionalServiceDAOImpl getInstance(){
        if(instance == null){
            instance = new AdditionalServiceDAOImpl();
        }
       return instance;
    }

}
