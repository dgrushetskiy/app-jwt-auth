package ru.gothmog.ws.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gothmog.ws.core.model.auth.Role;
import ru.gothmog.ws.core.model.auth.RoleName;
import ru.gothmog.ws.core.model.auth.Status;
import ru.gothmog.ws.core.model.auth.User;
import ru.gothmog.ws.core.repository.RoleRepository;
import ru.gothmog.ws.core.repository.UserRepository;
import ru.gothmog.ws.core.service.UserService;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwrdEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwrdEncoder;
    }

    @Override
    public User register(final User user) {
        Optional<Role> roleUser = roleRepository.findByRoleName(RoleName.ROLE_USER);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser.get());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User userReg = userRepository.save(user);

        log.info("In register user : {} successfully registered", userReg);

        return userReg;
    }

    @Override
    public User getByUserName(final String username) {
        final User user = userRepository.findByUsername(username);
        log.info("In findByUsername - user : {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User getById(Long id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null){
            log.warn("In findById no user found by id: {}", id);
            return null;
        }
        log.info("In findById - user : {} found by id: {}", user, id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("In deleteById - user with id: {} successfully delete",  id);
    }

    @Override
    public List<User> getUserAll() {
        List<User> users = userRepository.findAll();
        log.info("In getUserAll - {} users found", users.size());
        return users;
    }
}
