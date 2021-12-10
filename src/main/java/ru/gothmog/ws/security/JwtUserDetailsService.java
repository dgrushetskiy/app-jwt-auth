package ru.gothmog.ws.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gothmog.ws.core.model.auth.User;
import ru.gothmog.ws.core.service.UserService;
import ru.gothmog.ws.security.jwt.JwtUser;
import ru.gothmog.ws.security.jwt.JwtUserFactory;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + userName + " not found.");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", userName);
        return jwtUser;
    }
}
