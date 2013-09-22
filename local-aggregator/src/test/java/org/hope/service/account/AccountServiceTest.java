package org.hope.service.account;

import org.hope.core.entity.user.User;
import org.hope.core.service.account.AccountService;
import org.hope.runner.test.spring.Profiles;
import org.hope.runner.test.spring.SpringTransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

import static org.junit.Assert.*;

/**
 * AccountService的测试用例, 测试Service层的业务逻辑.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-root.xml"})
@Transactional
@ActiveProfiles(Profiles.DEVELOPMENT)
public class AccountServiceTest  extends SpringTransactionalTestCase {

    @Autowired
    private AccountService accountSerivce;

  /*  @Before
    public void setUp() {
       // MockitoAnnotations.initMocks(this);
    }*/
    @Rollback(value = false)
    @Test
    public void registerUser() {
        User user = new User();
        user.setName( "测试用户");
        user.setLoginName("test" + Math.random());
        user.setPlainPassword("123456");
        Date currentTime = new Date();
        accountSerivce.getAllUser() ;
        accountSerivce.registerUser(user);
        logger.info(user.toString());
        assertNotNull(user.getPassword());
        assertNotNull(user.getSalt());
    }


}
