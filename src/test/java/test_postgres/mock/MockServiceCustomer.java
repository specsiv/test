package test_postgres.mock;

import org.springframework.stereotype.Service;
import test_postgres.jpa.entity.Customer;
import test_postgres.service.ServiceCustomer;

import java.util.Arrays;
import java.util.List;

@Service
public class MockServiceCustomer implements ServiceCustomer {
    Customer customer = new Customer("Superman", true);
    List<Customer> list = Arrays.asList(customer, customer, customer);

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean vipStatusCheck(int id) {
        return true;
    }

    @Override
    public boolean customerCheck(int id) {
        return true;
    }

    @Override
    public boolean customerCheck(String fio) {
        return true;
    }

    @Override
    public List<Customer> getCustomersList() {
        return list;
    }

    @Override
    public Customer getCustomer(int id) {
        return customer;
    }

    @Override
    public Customer getCustomer(String fio) {
        return customer;
    }

    @Override
    public Customer update(int id, Customer customerData) {
        return customer;
    }
}