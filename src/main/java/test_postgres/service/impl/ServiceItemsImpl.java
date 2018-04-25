package test_postgres.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import test_postgres.exception.APIException;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;
import test_postgres.jpa.entity.Items;
import test_postgres.jpa.repository.RepositoryItems;
import test_postgres.service.ServiceItems;

import java.util.List;

@Service
public class ServiceItemsImpl implements ServiceItems {
    private final RepositoryItems repository;

    @Autowired
    public ServiceItemsImpl(RepositoryItems repository) {
        this.repository = repository;
    }

    @Override
    public void save(Items item) {
        try {
            repository.save(item);
        } catch (Exception e){
            throw new APIException("Произошла ошибка во время добавления товара!");
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
        repository.resetSequence();
    }

    @Override
    public void delete(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            try {
                repository.delete(id);
            } catch (EmptyResultDataAccessException e){
                throw new APINotFoundException("Товар с таким id не найден в базе!");
            } catch (Exception e) {
                throw new APIException("Произошла ошибка во время удаления товара из базы!");
            }
        }
    }

    @Override
    public boolean itemCheck(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");

        return repository.existsById(id);
    }

    @Override
    public boolean itemCheck(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Items getItem(String name) {
        Items item = repository.getByName(name);

        if (item != null) return item;
        else throw new APINotFoundException("Товара с таким именем нет в базе!");
    }

    @Override
    public Items getItem(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Items item = repository.findOne(id);

            if (item != null) return item;
            else throw new APINotFoundException("Товара с таким id нет в базе!");
        }
    }

    @Override
    public List<Items> getItemsList() {
        return repository.findAll();
    }

    @Override
    public Items update(int id, Items itemData) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Items item = repository.findOne(id);
            if (item != null){
                if (itemData != null){
                    item.setName(itemData.getName());
                    item.setWeight(itemData.getWeight());
                    item.setPrice(itemData.getPrice());

                    try {
                        repository.save(item);

                        return item;
                    } catch (Exception e){
                        throw new APIException("Произошла ошибка во время изменения данных товара!");
                    }
                } else throw new APIIllegalArgumentException("Не указаны новые данные товара!");
            } else throw new APINotFoundException("Товара с таким id нет в базе!");
        }
    }
}