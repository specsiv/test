package test_postgres.service.managers;

import org.springframework.beans.factory.annotation.Autowired;
import test_postgres.jpa.entity.Basket;
import test_postgres.jpa.entity.Customer;
import test_postgres.jpa.entity.Items;
import test_postgres.jpa.entity.Orders;
import test_postgres.service.ServiceBasket;
import test_postgres.service.ServiceCustomer;
import test_postgres.service.ServiceItems;
import test_postgres.service.ServiceOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicesManager {
    private ServiceBasket serviceBasket;
    private ServiceOrder serviceOrder;
    private ServiceItems serviceItems;
    private ServiceCustomer serviceCustomer;

    @Autowired
    public ServicesManager(ServiceBasket serviceBasket, ServiceOrder serviceOrder,
                           ServiceItems serviceItems, ServiceCustomer serviceCustomer) {
        this.serviceBasket = serviceBasket;
        this.serviceOrder = serviceOrder;
        this.serviceItems = serviceItems;
        this.serviceCustomer = serviceCustomer;
    }

    public int registration(String fio, boolean vipStatus){
        Customer customer = new Customer(fio, vipStatus);
        serviceCustomer.save(customer);

        return customer.getId();
    }

    public void addItem(String name, int weight, int price){
        serviceItems.save(new Items(name, weight, price));
    }

    public String makeOrder(int customerId, List<String> itemsNames, int money){
        if (serviceCustomer.customerCheck(customerId))
        {
            List<Items> items = new ArrayList<>(itemsNames.size());
            for (String itemName: itemsNames)
            {
                Items item = serviceItems.getItem(itemName);
                if (item != null) items.add(item);
                else return "Error: Предмет -> " + itemName + " отсутствует в базе!";
            }

            int summaryPrice = 0;
            for (Items item: items) summaryPrice += item.getPrice();
            if (summaryPrice > money) return "Error: Недостаточно средств!";

            serviceOrder.save(new Orders(LocalDate.now(), customerId));
            items.forEach((item) -> serviceBasket.save(new Basket(item.getId(), 1)));

            return "Заказ принят!";
        }
        else return "Error: Нет покупателя с таким id в базе!";
    }

    public void clear(){
        serviceBasket.deleteAll();
        serviceOrder.deleteAll();
        serviceItems.deleteAll();
        serviceCustomer.deleteAll();
    }
}