package test_postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test_postgres.jpa.entity.Items;
import test_postgres.service.ServiceItems;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemsController {
    private final ServiceItems serviceItems;

    @Autowired
    public ItemsController(ServiceItems serviceItems) {
        this.serviceItems = serviceItems;
    }

    @GetMapping("/check/{name}")
    public boolean itemCheck(@PathVariable String name){
        return serviceItems.itemCheck(name);
    }

    @GetMapping("/get/byName/{name}")
    public Items get(@PathVariable String name){
        return serviceItems.getItem(name);
    }

    @PostMapping("/add")
    public Items add(@RequestBody Items item){
        serviceItems.save(item);

        return item;
    }

    @GetMapping("/getAll")
    public List<Items> getAll(){
        return serviceItems.getItemsList();
    }

    @GetMapping("/get/{id}")
    public Items get(@PathVariable int id){
        return serviceItems.getItem(id);
    }

    @PutMapping("/update/{id}")
    public Items update(@PathVariable int id, @RequestBody Items itemData){
        Items item = serviceItems.update(id, itemData);

        return item;
    }
}