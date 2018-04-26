package test_postgres.mock;

import test_postgres.jpa.entity.Items;
import test_postgres.service.ServiceItems;

import java.util.Arrays;
import java.util.List;

public class MockServiceItems implements ServiceItems {
    Items item = new Items("Superman", 1, 1);
    List<Items> list = Arrays.asList(item, item, item);

    @Override
    public void save(Items item) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean itemCheck(int id) {
        return true;
    }

    @Override
    public boolean itemCheck(String name) {
        return true;
    }

    @Override
    public Items getItem(String name) {
        return item;
    }

    @Override
    public Items getItem(int id) {
        return item;
    }

    @Override
    public List<Items> getItemsList() {
        return list;
    }

    @Override
    public Items update(int id, Items itemData) {
        return item;
    }
}