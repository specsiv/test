package test_postgres.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import test_postgres.exception.APIException;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;
import test_postgres.jpa.entity.Orders;
import test_postgres.jpa.repository.RepositoryOrder;
import test_postgres.service.ServiceOrder;

import java.util.List;

@Service
public class ServiceOrderImpl implements ServiceOrder {
    private final RepositoryOrder repository;

    @Autowired
    public ServiceOrderImpl(RepositoryOrder repository) {
        this.repository = repository;
    }

    @Override
    public void save(Orders order) {
        try {
            repository.save(order);
        } catch (Exception e){
            throw new APIException("Произошла ошибка во время добавления заказа!");
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
                throw new APINotFoundException("Заказ с таким id не найден в базе!");
            } catch (Exception e) {
                throw new APIException("Произошла ошибка во время удаления заказа из базы!");
            }
        }
    }

    @Override
    public boolean orderCheck(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");

        return repository.existsById(id);
    }

    @Override
    public Orders getOrder(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Orders order = repository.findOne(id);

            if (order != null) return order;
            else throw new APINotFoundException("Заказа с таким id нет в базе!");
        }
    }

    @Override
    public List<Orders> getOrdersList() {
        return repository.findAll();
    }

    @Override
    public Orders update(int id, Orders orderData) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Orders order = repository.findOne(id);
            if (order != null){
                if (orderData != null){
                    order.setCustomerId(orderData.getCustomerId());
                    order.setDate(orderData.getDate());

                    try {
                        repository.save(order);

                        return order;
                    } catch (Exception e){
                        throw new APIException("Произошла ошибка во время изменения данных заказа!");
                    }
                } else throw new APIIllegalArgumentException("Не указаны новые данные заказа!");
            } else throw new APINotFoundException("Заказа с таким id нет в базе!");
        }
    }
}