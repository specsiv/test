package test_postgres.app;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import test_postgres.service.ServiceBasket;
import test_postgres.service.ServiceCustomer;
import test_postgres.service.ServiceItems;
import test_postgres.service.ServiceOrder;
import test_postgres.service.managers.ServicesManager;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"test_postgres"})
@EntityScan(basePackages = {"test_postgres.jpa.entity"})
@EnableJpaRepositories(basePackages = {"test_postgres.jpa.repository"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        //BasicConfigurator.configure();

//        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
//
//        ServiceCustomer serviceCustomer = context.getBean(ServiceCustomer.class);
//        ServiceItems serviceItems = context.getBean(ServiceItems.class);
//        ServiceOrder serviceOrder = context.getBean(ServiceOrder.class);
//        ServiceBasket serviceBasket = context.getBean(ServiceBasket.class);
//
//        ServicesManager servicesManager = new ServicesManager(serviceBasket, serviceOrder,
//                serviceItems, serviceCustomer);
//
//        servicesManager.registration("Sboev IV", true);
//        servicesManager.registration("Nikolaev DB", false);
//        servicesManager.registration("Oblomov VM", true);
//
//        servicesManager.addItem("Кактус", 1000, 300);
//        servicesManager.addItem("Какаду", 2000, 60000);
//        servicesManager.addItem("Ноутбук", 2500, 50000);
//
//        List<String> itemsNames = Arrays.asList("Ноутбук", "Кактус", "Какаду");
//
//        String result = servicesManager.makeOrder(1, itemsNames, 110300);
//        //servicesManager.clear();
//        context.close();
//
//        System.out.println(result);
    }
}