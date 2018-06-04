package test_postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test_postgres.jpa.entity.Customer;
import test_postgres.service.ServiceCustomer;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private final ServiceCustomer serviceCustomer;

    @Autowired
    public CustomerController(ServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    @GetMapping("/check/{id}")
    public boolean customerCheck(@PathVariable int id){
        return serviceCustomer.customerCheck(id);
    }

    @GetMapping("/checkByName/{fio}")
    public boolean customerCheck(@PathVariable String fio){
        return serviceCustomer.customerCheck(fio);
    }

    @GetMapping("/check/vip/{id}")
    public boolean vipStatusCheck(@PathVariable int id){
        return serviceCustomer.vipStatusCheck(id);
    }

    @PostMapping("/add")
    public Customer add(@RequestBody Customer customer){
        serviceCustomer.save(customer);

        return customer;
    }

    @GetMapping("/getAll")
    public List<Customer> getAll(){
        return serviceCustomer.getCustomersList();
    }

    @GetMapping("/get/{id}")
    public Customer get(@PathVariable int id){
        return serviceCustomer.getCustomer(id);
    }

    @GetMapping("/getByName/{fio}")
    public Customer get(@PathVariable String fio){
        return serviceCustomer.getCustomer(fio);
    }

    @PutMapping("/update/{id}")
    public Customer update(@PathVariable int id, @RequestBody Customer customerData){
        Customer customer = serviceCustomer.update(id, customerData);

        return customer;
    }
}