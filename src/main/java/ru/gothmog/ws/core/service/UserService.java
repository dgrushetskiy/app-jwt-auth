package ru.gothmog.ws.core.service;

import ru.gothmog.ws.core.model.auth.User;

import java.util.List;


public interface UserService {

    User register(User user);

    User getByUserName(String username);

    User getById(Long id);

    void delete(Long id);

    List<User> getUserAll();
}
