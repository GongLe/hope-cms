package org.hope.dao;

import org.hope.core.dao.user.UserDao2;
import org.hope.runner.test.spring.Profiles;
import org.hope.runner.test.spring.SpringTransactionalTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityNotFoundException;

/**
 * test jpa2.0 abstract common dao
 */
@ContextConfiguration(locations = {"/applicationContext-root.xml"})
@ActiveProfiles(Profiles.DEVELOPMENT)
public class JpaDaoTest extends SpringTransactionalTestCase {
    private static Logger logger = LoggerFactory.getLogger(JpaDaoTest.class);
    @Autowired
    private UserDao2 userDao2;

    @Test
    public void jpqlDaoTest() throws Exception {
        //test get by id
        Assert.assertTrue(userDao2.findOne("") == null);
        userDao2.findAll();
        userDao2.findPageUser(new PageRequest(0, 10));
        userDao2.findByLoginName("");
        userDao2.findByLoginName("admin");
    }
    @Test(expected = EntityNotFoundException.class)
    public void testLoad(){
          userDao2.load("") ;
    }
}
