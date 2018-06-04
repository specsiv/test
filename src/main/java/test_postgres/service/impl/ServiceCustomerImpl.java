package test_postgres.service.impl;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import test_postgres.exception.APIException;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;
import test_postgres.jpa.entity.Customer;
import test_postgres.jpa.repository.RepositoryCustomer;
import test_postgres.service.ServiceCustomer;

import java.util.List;

@Service
public class ServiceCustomerImpl implements ServiceCustomer {
    private final RepositoryCustomer repository;

    @Autowired
    public ServiceCustomerImpl(RepositoryCustomer repository) {
        this.repository = repository;
    }

    @Override
    public void save(Customer customer) {
        try {
            repository.save(customer);
        } catch (Exception e){
            throw new APIException("Произошла ошибка во время добавления клиента!");
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
                throw new APINotFoundException("Клиент с таким id не найден в базе!");
            } catch (Exception e) {
                throw new APIException("Произошла ошибка во время удаления клиента из базы!");
            }
        }
    }

    @Override
    public boolean vipStatusCheck(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");

        try {
            return repository.vipStatusCheck(id);
        } catch (AopInvocationException e){
            throw new APINotFoundException("Клиента с таким id нет в базе!");
        }
    }

    @Override
    public boolean customerCheck(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");

        return repository.existsById(id);
    }

    @Override
    public boolean customerCheck(String fio) {
        return repository.existsByFio(fio);
    }

    @Override
    public List<Customer> getCustomersList() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Customer customer = repository.findOne(id);

            if (customer != null) return customer;
            else throw new APINotFoundException("Клиента с таким id нет в базе!");
        }
    }

    @Override
    public Customer getCustomer(String fio) {
        Customer customer = repository.findByFio(fio);

        if (customer != null) return customer;
        else throw new APIIllegalArgumentException("Клиента с таким ФИО нет в базе!");
    }

    @Override
    public Customer update(int id, Customer customerData) {
        if (id <= 0) throw new APIIllegalArgumentException("Id должен быть больше 0!");
        else {
            Customer customer = repository.findOne(id);
            if (customer != null){
                if (customerData != null){
                    customer.setFio(customerData.getFio());
                    customer.setVipStatus(customerData.isVipStatus());

                    try {
                        repository.save(customer);

                        return customer;
                    } catch (Exception e){
                        throw new APIException("Произошла ошибка во время изменения данных клиента!");
                    }
                } else throw new APIIllegalArgumentException("Не указаны новые данные клиента!");
            } else throw new APINotFoundException("Клиента с таким id нет в базе!");
        }
    }
}