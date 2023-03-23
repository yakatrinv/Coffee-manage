package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import it.academy.repositories.impl.CrudRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Data.FIRST_INDEX;
import static it.academy.utils.Data.ROLE_CLASS;
import static it.academy.utils.Data.ROLE_NAME;

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
                    .where(builder.equal(roleRoot.get(ROLE_NAME), roleName));

            List<Role> resultList = entityManager.createQuery(criteria).getResultList();
            if (!resultList.isEmpty()) {
                findRole = resultList.get(FIRST_INDEX);
            }

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
