package test_postgres.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import test_postgres.jpa.entity.Orders;
import test_postgres.service.ServiceOrder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockServiceOrder implements ServiceOrder {
    Orders elem = new Orders(LocalDate.parse("2000-01-01"), 1);
    List<Orders> list = Arrays.asList(elem, elem, elem);

    @Override
    public void save(Orders order) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean orderCheck(int id) {
        return true;
    }

    @Override
    public Orders getOrder(int id){
        return elem;
    }

    @Override
    public List<Orders> getOrdersList() {
        return list;
    }

    @Override
    public Orders update(int id, Orders orderData) {
        return elem;
    }
}