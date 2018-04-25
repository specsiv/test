package test_postgres.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import test_postgres.config.TestConfig;
import test_postgres.jpa.repository.RepositoryCustomer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class RepositoryCustomerTest {
    @Autowired
    private RepositoryCustomer repositoryCustomer;

    @Test
    public void vipStatusCheckTest(){
        Assert.assertEquals(repositoryCustomer.vipStatusCheck(1), true);
        Assert.assertEquals(repositoryCustomer.vipStatusCheck(2), false);
        Assert.assertEquals(repositoryCustomer.vipStatusCheck(3), true);
    }

    @Test(expected = AopInvocationException.class)
    public void vipStatusCheckExTest(){
        try {
            repositoryCustomer.vipStatusCheck(0);
        } catch (AopInvocationException e) {
            repositoryCustomer.vipStatusCheck(-1);
        }
    }
}