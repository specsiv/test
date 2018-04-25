package test_postgres.service;

import test_postgres.jpa.entity.Items;

import java.util.List;

public interface ServiceItems {
    void save(Items item);
    void deleteAll();
    void delete(int id);
    boolean itemCheck(int id);
    boolean itemCheck(String name);
    Items getItem(String name);
    Items getItem(int id);
    List<Items> getItemsList();
    Items update(int id, Items itemData);
}