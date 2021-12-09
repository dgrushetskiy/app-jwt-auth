package ru.gothmog.ws.core.model.auth;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ru.gothmog.ws.core.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "jwt_auth",
        indexes = {@Index(name = "unq_role_name", columnList = "role_name", unique = true)})
@Data
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name", length = 100)
    private RoleName roleName;

}
