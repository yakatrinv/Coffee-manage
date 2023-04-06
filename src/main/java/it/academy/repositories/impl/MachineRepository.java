package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.repositories.IMachineRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Set;

import static it.academy.utils.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.DataGeneral.PRODUCT_CLASS;

public class MachineRepository extends CrudRepository<Machine>
        implements IMachineRepository {
    private EntityManager entityManager;

    public MachineRepository() {
        super(MACHINE_CLASS);
    }

    @Override
    public void addProductInMachine(Integer machineId, Integer productId) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Set<Product> products = machine.getProducts();
            if (!products.contains(product)) {
                products.add(product);
                machine.setProducts(products);

                entityManager.merge(machine);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteProductInMachine(Integer machineId, Integer productId) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Set<Product> products = machine.getProducts();
            if (products.contains(product)) {
                products.remove(product);
                machine.setProducts(products);

                entityManager.merge(machine);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
