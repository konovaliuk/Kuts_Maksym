package dao.impl;

import dao.OrderDAO;
import dao.util.DBUtil;
import enteties.Order;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public Order findOrderById(Long id) {
        String SQL = "SELECT * FROM order WHERE order_id = ?";
        List<Order> orders = findOrdersDynamically(SQL,id);
        if(orders.size()>0){
            return orders.get(0);
        }
        return null;
    }

    @Override
    public List<Order> findAllOrders() {
        String SQL = "SELECT * FROM order";
        return findOrdersDynamically(SQL);
    }

    @Override
    public List<Order> findOrdersDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.Order,sql,values);
    }
}
