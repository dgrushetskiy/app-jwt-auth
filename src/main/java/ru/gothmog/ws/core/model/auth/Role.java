package ru.gothmog.ws.core.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ru.gothmog.ws.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Table(name = "roles", schema = "jwt_auth",
        indexes = {@Index(name = "unq_role_name", columnList = "role_name", unique = true)})
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name", length = 100)
    private RoleName roleName;
}
