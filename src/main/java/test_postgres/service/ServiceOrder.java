package test_postgres.service;

import test_postgres.jpa.entity.Orders;

import java.util.List;

public interface ServiceOrder {
    void save(Orders order);
    void deleteAll();
    void delete(int id);
    boolean orderCheck(int id);
    Orders getOrder(int id);
    List<Orders> getOrdersList();
    Orders update(int id, Orders orderData);
}