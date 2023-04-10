package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {
    public static final String NEW_ROLE = "New role";
    IRoleRepository roleRepository = new RoleRepository();

    @Test
    void isShouldFindRoleByName() {
        Role newRole=Role.builder()
                .roleName(NEW_ROLE)
                .build();

        roleRepository.save(newRole);

        Role findRole = roleRepository.findByRoleName(NEW_ROLE);

        assertAll(
                () -> assertNotNull(findRole),
                () -> assertEquals(NEW_ROLE,findRole.getRoleName())
        );

        roleRepository.delete(findRole.getId());
    }


}