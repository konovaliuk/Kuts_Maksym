package dao;

import enteties.Order;
import java.util.List;

    public interface OrderDAO {

        Order findOrderById(Long id);
        List<Order> findAllOrders();
        List<Order> findOrdersDynamically(String sql, Object... values);
}
