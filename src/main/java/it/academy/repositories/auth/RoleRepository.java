package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import it.academy.repositories.impl.CrudRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static it.academy.utils.DataAuth.ATTR_ROLE_NAME;
import static it.academy.utils.DataGeneral.ROLE_CLASS;

public class RoleRepository extends CrudRepository<Role>
        implements IRoleRepository {
    private EntityManager entityManager;

    public RoleRepository() {
        super(ROLE_CLASS);
    }

    @Override
    public Role findByRoleName(String roleName) {
        Role findRole = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> criteria = builder.createQuery(ROLE_CLASS);
            Root<Role> roleRoot = criteria.from(ROLE_CLASS);

            criteria.select(roleRoot)
                    .where(builder.equal(roleRoot.get(ATTR_ROLE_NAME), roleName));

            findRole = entityManager.createQuery(criteria).getSingleResult();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return findRole;
    }
}
