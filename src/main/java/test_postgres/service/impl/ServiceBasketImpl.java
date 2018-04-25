package test_postgres.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import test_postgres.exception.APIException;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;
import test_postgres.jpa.entity.Basket;
import test_postgres.jpa.repository.RepositoryBasket;
import test_postgres.service.ServiceBasket;

import java.util.List;

@Service
public class ServiceBasketImpl implements ServiceBasket {
    private final RepositoryBasket repository;

    @Autowired
    public ServiceBasketImpl(RepositoryBasket repository) {
        this.repository = repository;
    }

    @Override
    public void save(Basket basket) {
        try {
            repository.save(basket);
        }catch (Exception e){
            throw new APIException("Произошла ошибка во время добавления товара в корзину!");
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
                throw new APINotFoundException("Корзина с таким id не найдена в базе!");
            } catch (Exception e) {
                throw new APIException("Произошла ошибка во время удаления из корзины!");
            }
        }
    }

    @Override
    public boolean basketCheck(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");

        return repository.existsById(id);
    }

    @Override
    public Basket getBasket(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Basket basket = repository.findOne(id);

            if (basket != null) return basket;
            else throw new APINotFoundException("Записи корзины с таким id нет в базе!");
        }
    }

    @Override
    public List<Basket> getBasketsList() {
        return repository.findAll();
    }

    @Override
    public Basket update(int id, Basket basketData) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Basket basket = repository.findOne(id);
            if (basket != null){
                if (basketData != null){
                    basket.setItemId(basketData.getItemId());
                    basket.setOrderId(basketData.getOrderId());

                    try {
                        repository.save(basket);

                        return basket;
                    } catch (Exception e){
                        throw new APIException("Произошла ошибка во время изменения данных корзины!");
                    }
                } else throw new APIIllegalArgumentException("Не указаны новые данные товара в корзине!");
            } else throw new APINotFoundException("Записи корзины с таким id нет в базе!");
        }
    }
}