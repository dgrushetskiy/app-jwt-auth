package ru.gothmog.ws.core.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import ru.gothmog.ws.core.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", schema = "jwt_auth",
        indexes = {
        @Index(name = "unq_user_email", columnList = "email", unique = true),
        @Index(name = "unq_user_username", columnList = "username", unique = true)
})
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NaturalId
    @NotBlank
    @Size(max = 100)
    @Email
    @Column(name = "email", length = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(name = "passwd", length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", schema = "jwt_auth",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_roles_user_id")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_roles_role_id")))
    private Set<Role> roles = new HashSet<>();
}
