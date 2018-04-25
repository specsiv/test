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
import test_postgres.jpa.entity.Basket;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ServiceBasketTest {
    @Autowired
    private ServiceBasket serviceBasket;

    private Basket testBasket;
    private int SupermanId = 555;

    @Before
    public void init(){
        testBasket = new Basket(1, 2);
        serviceBasket.save(testBasket);
        SupermanId = testBasket.getId();
    }

    @After
    public void quit(){
        if (serviceBasket.basketCheck(SupermanId))
            serviceBasket.delete(SupermanId);
    }

    @Test
    public void saveTest(){
        Assert.assertEquals(serviceBasket.basketCheck(SupermanId), true);
    }

    @Test(expected = APIException.class)
    public void saveExTest(){
        serviceBasket.save(null);
    }

    @Test
    public void deleteTest(){
        serviceBasket.delete(SupermanId);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void deleteIAExTest(){
        try{
            serviceBasket.delete(0);
        } catch (APIIllegalArgumentException e){
            serviceBasket.delete(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void deleteNFExTest(){
        serviceBasket.delete(53);
    }

    @Test
    public void basketCheckTest(){
        Assert.assertEquals(serviceBasket.basketCheck(1), true);
        Assert.assertEquals(serviceBasket.basketCheck(2), true);
        Assert.assertEquals(serviceBasket.basketCheck(3), true);
        Assert.assertEquals(serviceBasket.basketCheck(4), true);
        Assert.assertEquals(serviceBasket.basketCheck(SupermanId), true);
        Assert.assertEquals(serviceBasket.basketCheck(53), false);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void basketCheckIAExTest(){
        try{
            serviceBasket.basketCheck(0);
        } catch (APIIllegalArgumentException e){
            serviceBasket.basketCheck(-1);
        }
    }

    @Test
    public void getBasketTest(){
        Assert.assertEquals(serviceBasket.getBasket(1).getItemId(), 1);
        Assert.assertEquals(serviceBasket.getBasket(2).getItemId(), 2);
        Assert.assertEquals(serviceBasket.getBasket(3).getItemId(), 3);
        Assert.assertEquals(serviceBasket.getBasket(4).getItemId(), 2);
        Assert.assertEquals(serviceBasket.getBasket(SupermanId).getItemId(), 1);;
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void getBasketIAExTest(){
        try{
            serviceBasket.getBasket(0);
        } catch (APIIllegalArgumentException e){
            serviceBasket.getBasket(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void getBasketNFExTest(){
        serviceBasket.getBasket(53);
    }

    @Test
    public void getBasketsListTest(){
        Assert.assertEquals(serviceBasket.getBasketsList().size(), 5);
    }

    @Test
    public void updateTest(){
        serviceBasket.update(SupermanId, new Basket(2, 2));
        //serviceItems.update(SupermanId, new Items("Superman", 1, 1));
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateIAExTest(){
        try{
            serviceBasket.update(0, null);
        } catch (APIIllegalArgumentException e){
            serviceBasket.update(-1, null);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void updateNFExTest(){
        serviceBasket.update(53, null);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateNFExTest2(){
        serviceBasket.update(SupermanId, null);
    }
}