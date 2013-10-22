package org.lework.core.dao;

import org.lework.runner.spring.Profiles;
import org.lework.runner.spring.SpringTransactionalTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"/applicationContext-root.xml"})
@ActiveProfiles(Profiles.DEVELOPMENT)
public class JpaMappingTest extends SpringTransactionalTestCase {

    private static Logger logger = LoggerFactory.getLogger(JpaMappingTest.class);

    @PersistenceContext
    private EntityManager em;

    @Test
    public void allClassMapping() throws Exception {
        Metamodel model = em.getEntityManagerFactory().getMetamodel();

        assertTrue("No entity mapping found", model.getEntities().size() > 0);

        for (EntityType entityType : model.getEntities()) {
            String entityName = entityType.getName();
            em.createQuery("select o from " + entityName + " o").getResultList();
            logger.info("ok: " + entityName);

        }
    }
}
