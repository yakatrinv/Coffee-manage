package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.repositories.impl.CrudRepository;
import it.academy.services.auth.EncryptService;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.FIRST_INDEX;
import static it.academy.utils.Data.USER_CLASS;

public class UserRepository extends CrudRepository<User>
        implements IUserRepository {
    EncryptService encryptService = new EncryptService();

    private EntityManager entityManager;

    public UserRepository() {
        super(USER_CLASS);
    }

    @Override
    public User findAuthUser(String login, String pass) {
        User findUser = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(USER_CLASS);
            Root<User> userRoot = criteria.from(USER_CLASS);

            criteria.select(userRoot)
                    .where(builder.equal(userRoot.get(ATTR_LOGIN), login));

            List<User> resultList = entityManager
                    .createQuery(criteria)
                    .getResultList();
            if (!resultList.isEmpty()) {
                findUser = resultList.get(FIRST_INDEX);
                Set<Role> roles = new HashSet<>();
                findUser.getRoles().forEach(role -> roles.add(Role.builder()
                        .id(role.getId())
                        .roleName(role.getRoleName())
                        .build()));

                findUser.setRoles(roles);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        findUser = checkVerify(pass, findUser);

        return findUser;
    }

    private User checkVerify(String pass, User findUser) {
        if (findUser != null) {
            byte[] salt = encryptService.fromHex(findUser.getSalt());
            byte[] encryptPass = encryptService.fromHex(findUser.getPassword());

            boolean verifyUser = encryptService.verifyPassword(pass, encryptPass, salt);

            if (!verifyUser) {
                findUser = null;
            }
        }
        return findUser;
    }

    @Override
    public User findByLogin(String login) {
        User findUser = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(USER_CLASS);
            Root<User> userRoot = criteria.from(USER_CLASS);

            criteria.select(userRoot)
                    .where(builder.equal(userRoot.get(ATTR_LOGIN), login));

            List<User> resultList = entityManager
                    .createQuery(criteria)
                    .getResultList();
            if (!resultList.isEmpty()) {
                findUser = resultList.get(FIRST_INDEX);
                Set<Role> roles = new HashSet<>();
                findUser.getRoles().forEach(role -> roles.add(Role.builder()
                        .id(role.getId())
                        .roleName(role.getRoleName())
                        .build()));

                findUser.setRoles(roles);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return findUser;
    }

    @Override
    public Optional<User> save(User user) {
        setEncryptData(user);
        return super.save(user);
    }

    private void setEncryptData(User user) {
        byte[] salt = encryptService.generateSalt();
        byte[] encryptedPassword = encryptService
                .getEncryptedPassword(user.getPassword(), salt);
        user.setSalt(encryptService.toHex(salt));
        user.setPassword(encryptService.toHex(encryptedPassword));
    }
}
