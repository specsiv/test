package test_postgres.service;

import test_postgres.jpa.entity.Basket;

import java.util.List;

public interface ServiceBasket {
    void save(Basket basket);
    void deleteAll();
    void delete(int id);
    boolean basketCheck(int id);
    Basket getBasket(int id);
    List<Basket> getBasketsList();
    Basket update(int id, Basket basketData);
}