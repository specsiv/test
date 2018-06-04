package test_postgres.service;

import test_postgres.jpa.entity.Customer;

import java.util.List;

public interface ServiceCustomer {
    void save(Customer customer);
    void deleteAll();
    void delete(int id);
    boolean vipStatusCheck(int id);
    boolean customerCheck(int id);
    boolean customerCheck(String fio);
    List<Customer> getCustomersList();
    Customer getCustomer(int id);
    Customer getCustomer(String fio);
    Customer update(int id, Customer customerData);
}