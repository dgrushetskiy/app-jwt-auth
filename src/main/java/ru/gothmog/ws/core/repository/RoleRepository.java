package ru.gothmog.ws.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gothmog.ws.core.model.auth.Role;
import ru.gothmog.ws.core.model.auth.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
}
