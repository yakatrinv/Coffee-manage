package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import it.academy.repositories.ICrudRepository;

public interface IRoleRepository extends ICrudRepository<Role> {
    Role findByRoleName(String role);
}
