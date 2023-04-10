package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.utils.HibernateUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static it.academy.utils.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.DataGeneral.PRODUCT_CLASS;

class MachineRepositoryTest {
    private EntityManager entityManager;

    @Test
    void TestMethod() {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder =
                    entityManager.getCriteriaBuilder();

            CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

            Root<Machine> machineRoot = query.from(MACHINE_CLASS);
            Root<Product> productRoot = query.from(PRODUCT_CLASS);

            CriteriaQuery<Object[]> multiselect = query.multiselect(machineRoot, productRoot);
            TypedQuery<Object[]> typedQuery = entityManager.createQuery(multiselect);
            List<Object[]> resultList = typedQuery.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}