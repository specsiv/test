package test_postgres.service;

import test_postgres.jpa.entity.Customer;

import java.util.List;

public interface ServiceCustomer {
    void save(Customer customer);
    void deleteAll();
    void delete(int id);
    boolean vipStatusCheck(int id);
    boolean customerCheck(int id);
    List<Customer> getCustomersList();
    Customer getCustomer(int id);
    Customer update(int id, Customer customerData);
}