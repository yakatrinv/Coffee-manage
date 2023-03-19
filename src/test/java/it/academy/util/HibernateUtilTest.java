package it.academy.util;

import it.academy.utils.HibernateUtil;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HibernateUtilTest {
    @Test
    public void TestConnection() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        assertNotNull(entityManager);
        entityManager.close();
        HibernateUtil.closeFactory();
        assertTrue(((SessionImpl) entityManager).isClosed());
    }

}