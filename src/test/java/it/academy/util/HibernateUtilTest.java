package it.academy.util;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
class HibernateUtilTest {
    @Test
    public void TestConnection() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        assertNotNull(entityManager);
        HibernateUtil.closeFactory();
    }
  
}