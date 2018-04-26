package test_postgres.mock;

import test_postgres.jpa.entity.Basket;
import test_postgres.service.ServiceBasket;

import java.util.Arrays;
import java.util.List;

public class MockServiceBasket implements ServiceBasket {
    Basket elem = new Basket(1,1);
    List<Basket> list = Arrays.asList(elem, elem, elem);

    @Override
    public void save(Basket basket) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean basketCheck(int id) {
        return true;
    }

    @Override
    public Basket getBasket(int id) {
        return elem;
    }

    @Override
    public List<Basket> getBasketsList() {
        return list;
    }

    @Override
    public Basket update(int id, Basket basketData) {
        return elem;
    }
}