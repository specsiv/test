package test_postgres.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import test_postgres.config.TestConfig;
import test_postgres.exception.APIException;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;
import test_postgres.jpa.entity.Orders;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ServiceOrderTest {
    @Autowired
    private ServiceOrder serviceOrder;

    private Orders testOrder;
    private int SupermanId = 555;

    @Before
    public void init(){
        testOrder = new Orders(LocalDate.now(), 3);
        serviceOrder.save(testOrder);
        SupermanId = testOrder.getId();
    }

    @After
    public void quit(){
        if (serviceOrder.orderCheck(SupermanId))
            serviceOrder.delete(SupermanId);
    }

    @Test
    public void saveTest(){
        Assert.assertEquals(serviceOrder.orderCheck(SupermanId), true);
    }

    @Test(expected = APIException.class)
    public void saveExTest(){
        serviceOrder.save(null);
    }

    @Test
    public void deleteTest(){
        serviceOrder.delete(SupermanId);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void deleteIAExTest(){
        try{
            serviceOrder.delete(0);
        } catch (APIIllegalArgumentException e){
            serviceOrder.delete(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void deleteNFExTest(){
        serviceOrder.delete(53);
    }

    @Test
    public void orderCheckTest(){
        Assert.assertEquals(serviceOrder.orderCheck(1), true);
        Assert.assertEquals(serviceOrder.orderCheck(2), true);
        Assert.assertEquals(serviceOrder.orderCheck(SupermanId), true);
        Assert.assertEquals(serviceOrder.orderCheck(53), false);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void orderCheckIAExTest(){
        try{
            serviceOrder.orderCheck(0);
        } catch (APIIllegalArgumentException e){
            serviceOrder.orderCheck(-1);
        }
    }

    @Test
    public void getOrderTest(){
        Assert.assertEquals(serviceOrder.getOrder(1).getDate().toString(), "2018-04-19");
        Assert.assertEquals(serviceOrder.getOrder(2).getDate().toString(), "2022-05-26");
        Assert.assertEquals(serviceOrder.getOrder(SupermanId).getDate(), LocalDate.now());
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void getOrderIAExTest(){
        try{
            serviceOrder.getOrder(0);
        } catch (APIIllegalArgumentException e){
            serviceOrder.getOrder(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void getOrderNFExTest(){
        serviceOrder.getOrder(53);
    }

    @Test
    public void getOrdersListTest(){
        Assert.assertEquals(serviceOrder.getOrdersList().size(), 3);
    }

    @Test
    public void updateTest(){
        serviceOrder.update(SupermanId, new Orders(LocalDate.now(), 1));
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateIAExTest(){
        try{
            serviceOrder.update(0, null);
        } catch (APIIllegalArgumentException e){
            serviceOrder.update(-1, null);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void updateNFExTest(){
        serviceOrder.update(53, null);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateNFExTest2(){
        serviceOrder.update(SupermanId, null);
    }
}