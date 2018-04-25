package test_postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test_postgres.jpa.entity.Orders;
import test_postgres.service.ServiceOrder;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final ServiceOrder serviceOrder;

    @Autowired
    public OrderController(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @PostMapping("/add")
    public Orders add(@RequestBody Orders order){
        serviceOrder.save(order);

        return order;
    }

    @GetMapping("/getAll")
    public List<Orders> getAll(){
        return serviceOrder.getOrdersList();
    }

    @GetMapping("/get/{id}")
    public Orders get(@PathVariable int id){
        return serviceOrder.getOrder(id);
    }

    @PutMapping("/update/{id}")
    public Orders update(@PathVariable int id, @RequestBody Orders orderData){
        Orders order = serviceOrder.update(id, orderData);

        return order;
    }
}