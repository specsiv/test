package test_postgres.controller;

import org.springframework.web.bind.annotation.*;
import test_postgres.jpa.entity.Basket;
import test_postgres.service.ServiceBasket;

import java.util.List;

@RestController
@RequestMapping("api/basket")
public class BasketController {
    private final ServiceBasket serviceBasket;

    public BasketController(ServiceBasket serviceBasket) {
        this.serviceBasket = serviceBasket;
    }

    @PostMapping("/add")
    public Basket add(@RequestBody Basket basket){
        serviceBasket.save(basket);

        return basket;
    }

    @GetMapping("/getAll")
    public List<Basket> getAll(){
        return serviceBasket.getBasketsList();
    }

    @GetMapping("/get/{id}")
    public Basket get(@PathVariable int id){
        return serviceBasket.getBasket(id);
    }

    @PutMapping("/update/{id}")
    public Basket update(@PathVariable int id, @RequestBody Basket basketData){
        Basket basket = serviceBasket.update(id, basketData);

        return basket;
    }
}