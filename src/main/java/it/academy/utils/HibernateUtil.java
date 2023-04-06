package it.academy.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static it.academy.utils.DataGeneral.PERSISTENCE_NAME;

/**
 * @author Katerina
 * @version 2.0
 * util class:get property connection,
 * create entity manager.
 */
public class HibernateUtil {
    /**
     * get connect property.
     */
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

    /**
     * create entity manager
     *
     * @return object EntityManager
     */
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    /**
     * close entity manager factory
     */
    public static void closeFactory() {
        FACTORY.close();
    }
}
