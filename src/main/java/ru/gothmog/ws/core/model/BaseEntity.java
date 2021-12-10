package ru.gothmog.ws.core.model;

import lombok.Data;
import ru.gothmog.ws.core.model.auth.Status;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 25)
    private Status status;
}
