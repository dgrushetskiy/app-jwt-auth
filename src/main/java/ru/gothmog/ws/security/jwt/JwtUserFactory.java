package ru.gothmog.ws.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.gothmog.ws.core.model.auth.Role;
import ru.gothmog.ws.core.model.auth.Status;
import ru.gothmog.ws.core.model.auth.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new HashSet<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdatedAt()
        );
    }

    private static Set<? extends GrantedAuthority> mapToGrantedAuthorities(HashSet<Role> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toSet());
    }
}
