package org.lework.core.dao;

import org.lework.core.dao.user.UserNativeDao;
import org.lework.runner.spring.Profiles;
import org.lework.runner.spring.SpringTransactionalTestCase;
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
    private UserNativeDao userNativeDao;

    @Test
    public void jpqlDaoTest() throws Exception {
        //test get by id
        Assert.assertTrue(userNativeDao.findOne("") == null);
        userNativeDao.findAll();
        userNativeDao.findPageUser(new PageRequest(0, 10));
        userNativeDao.findByLoginName("");
        userNativeDao.findByLoginName("admin");
    }
    @Test(expected = EntityNotFoundException.class)
    public void testLoad(){
          userNativeDao.load("") ;
    }
}
