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
import test_postgres.jpa.entity.Items;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ServiceItemsTest {
    @Autowired
    private ServiceItems serviceItems;

    private Items testItem;
    private int SupermanId = 555;

    @Before
    public void init(){
        testItem = new Items("Superman", 1, 1);
        serviceItems.save(testItem);
        SupermanId = testItem.getId();
    }

    @After
    public void quit(){
        if (serviceItems.itemCheck(SupermanId))
            serviceItems.delete(SupermanId);
    }

    @Test
    public void saveTest(){
        Assert.assertEquals(serviceItems.itemCheck(SupermanId), true);
    }

    @Test(expected = APIException.class)
    public void saveExTest(){
        serviceItems.save(null);
    }

    @Test
    public void deleteTest(){
        serviceItems.delete(SupermanId);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void deleteIAExTest(){
        try{
            serviceItems.delete(0);
        } catch (APIIllegalArgumentException e){
            serviceItems.delete(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void deleteNFExTest(){
        serviceItems.delete(53);
    }

    @Test
    public void itemCheckTest(){
        Assert.assertEquals(serviceItems.itemCheck(1), true);
        Assert.assertEquals(serviceItems.itemCheck(2), true);
        Assert.assertEquals(serviceItems.itemCheck(3), true);
        Assert.assertEquals(serviceItems.itemCheck(SupermanId), true);
        Assert.assertEquals(serviceItems.itemCheck(53), false);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void itemCheckIAExTest(){
        try{
            serviceItems.itemCheck(0);
        } catch (APIIllegalArgumentException e){
            serviceItems.itemCheck(-1);
        }
    }

    @Test
    public void itemCheckByNameTest(){
        Assert.assertEquals(serviceItems.itemCheck("Кактус"), true);
        Assert.assertEquals(serviceItems.itemCheck("Какаду"), true);
        Assert.assertEquals(serviceItems.itemCheck("Ананасик"), true);
        Assert.assertEquals(serviceItems.itemCheck("Superman"), true);
        Assert.assertEquals(serviceItems.itemCheck("Покемон"), false);
    }

    @Test
    public void getItemByNameTest(){
        Assert.assertEquals(serviceItems.getItem("Кактус").getId(), 1);
        Assert.assertEquals(serviceItems.getItem("Какаду").getId(), 2);
        Assert.assertEquals(serviceItems.getItem("Ананасик").getId(), 3);
        Assert.assertEquals(serviceItems.getItem("Superman").getId(), SupermanId);
    }

    @Test(expected = APINotFoundException.class)
    public void getItemByNameNFExTest(){
        serviceItems.getItem("Покемон");
    }

    @Test
    public void getItemTest(){
        Assert.assertEquals(serviceItems.getItem(1).getName(), "Кактус");
        Assert.assertEquals(serviceItems.getItem(2).getName(), "Какаду");
        Assert.assertEquals(serviceItems.getItem(3).getName(), "Ананасик");
        Assert.assertEquals(serviceItems.getItem(SupermanId).getName(), "Superman");
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void getItemIAExTest(){
        try{
            serviceItems.getItem(0);
        } catch (APIIllegalArgumentException e){
            serviceItems.getItem(-1);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void getItemNFExTest(){
        serviceItems.getItem(53);
    }

    @Test
    public void getItemsListTest(){
        Assert.assertEquals(serviceItems.getItemsList().size(), 4);
    }

    @Test
    public void updateTest(){
        serviceItems.update(SupermanId, new Items("Spider-man", 2, 2));
        //serviceItems.update(SupermanId, new Items("Superman", 1, 1));
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateIAExTest(){
        try{
            serviceItems.update(0, null);
        } catch (APIIllegalArgumentException e){
            serviceItems.update(-1, null);
        }
    }

    @Test(expected = APINotFoundException.class)
    public void updateNFExTest(){
        serviceItems.update(53, null);
    }

    @Test(expected = APIIllegalArgumentException.class)
    public void updateNFExTest2(){
        serviceItems.update(SupermanId, null);
    }
}