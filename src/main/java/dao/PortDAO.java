package dao;


import enteties.Port;

import java.util.List;

public interface PortDAO {

    Port findPortById(Long id);
    List<Port> findAllPorts();
    List<Port> findPortsDynamically(String sql, Object... values);

}
