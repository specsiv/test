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
import test_postgres.jpa.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ServiceCustomerTest {
    @Autowired
    private ServiceCustomer serviceCustomer;

    private Customer testCustomer;
    private int SupermanId = 555;

    @Before
    public void init(){
        testCustomer = new Customer("Superman", true);
        serviceCustomer.save(testCustomer);
        SupermanId = testCustomer.getId();
    }

    @After
    public void quit(){
        if (serviceCustomer.customerCheck(SupermanId))
            serviceCustomer.delete(SupermanId);
    }

    @Test
    public void customerCheckTest(){
        Assert.assertEquals(serviceCustomer.customerCheck(1), true);
        Assert.assertEquals(serviceCustomer.customerCheck(2), true);
        Assert.assertEquals(serviceCustomer.customerCheck(3), true);
        Assert.assertEquals(serviceCustomer.customerCheck(SupermanId), true);
        Assert.assertEquals(serviceCustomer.customerCheck(53), false);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void customerCheckIAExTest(){
        try{
            serviceCustomer.customerCheck(0);
        } catch (APIIllegalArgumentException e){
            serviceCustomer.customerCheck(-1);
        }
    }

    @Test
    public void vipStatusCheckTest(){
        Assert.assertEquals(serviceCustomer.vipStatusCheck(1), true);
        Assert.assertEquals(serviceCustomer.vipStatusCheck(2), false);
        Assert.assertEquals(serviceCustomer.vipStatusCheck(3), true);
        Assert.assertEquals(serviceCustomer.vipStatusCheck(SupermanId), true);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void vipStatusIAExCheckTest(){
        try{
            serviceCustomer.vipStatusCheck(0);
        } catch (APIIllegalArgumentException e){
            serviceCustomer.vipStatusCheck(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void vipStatusNFExCheckTest(){
        serviceCustomer.vipStatusCheck(53);
    }

    @Test
    public void saveTest(){
        Assert.assertEquals(serviceCustomer.customerCheck(SupermanId), true);
    }

    @Test(expected = APIException.class)
    public void saveExTest(){
        serviceCustomer.save(null);
    }

    @Test
    public void getCustomersListTest(){
        Assert.assertEquals(serviceCustomer.getCustomersList().size(), 4);
    }

    @Test
    public void getCustomerTest(){
        Assert.assertEquals(serviceCustomer.getCustomer(1).getFio(), "Ivan");
        Assert.assertEquals(serviceCustomer.getCustomer(2).getFio(), "Vitya");
        Assert.assertEquals(serviceCustomer.getCustomer(3).getFio(), "Dudian");
        Assert.assertEquals(serviceCustomer.getCustomer(SupermanId).getFio(), "Superman");
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void getCustomerIAExTest(){
        try{
            serviceCustomer.getCustomer(0);
        } catch (APIIllegalArgumentException e){
            serviceCustomer.getCustomer(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void getCustomerNFExTest(){
        serviceCustomer.getCustomer(53);
    }

    @Test
    public void deleteTest(){
        serviceCustomer.delete(SupermanId);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void deleteIAExTest(){
        try{
            serviceCustomer.delete(0);
        } catch (APIIllegalArgumentException e){
            serviceCustomer.delete(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void deleteNFExTest(){
        serviceCustomer.delete(53);
    }

    @Test
    public void updateTest(){
        serviceCustomer.update(SupermanId, new Customer("Spider-man", false));
        //serviceCustomer.update(SupermanId, new Customer("Superman", true));
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateIAExTest(){
        try{
            serviceCustomer.update(0, null);
        } catch (APIIllegalArgumentException e){
            serviceCustomer.update(-1, null);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void updateNFExTest(){
        serviceCustomer.update(53, null);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateNFExTest2(){
        serviceCustomer.update(SupermanId, null);
    }
}