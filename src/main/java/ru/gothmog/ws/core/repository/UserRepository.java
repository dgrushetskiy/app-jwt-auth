package ru.gothmog.ws.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gothmog.ws.core.model.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);
}
