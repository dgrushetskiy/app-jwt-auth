CREATE SCHEMA IF NOT EXISTS jwt_auth;

CREATE TABLE IF NOT EXISTS jwt_auth.roles (
                                 id                   bigserial  NOT NULL ,
                                 created_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                                 updated_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                                 status               varchar(25) DEFAULT 'ACTIVE' NOT NULL ,
                                 role_name            varchar(100)  NOT NULL ,
                                 CONSTRAINT pk_roles_id PRIMARY KEY ( id ),
                                 CONSTRAINT unq_role_name UNIQUE ( role_name )
);

CREATE TABLE IF NOT EXISTS jwt_auth.users (
                                 id                   bigserial  NOT NULL ,
                                 created_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                                 updated_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                                 status               varchar(25) DEFAULT 'ACTIVE' NOT NULL ,
                                 username             varchar(100)  NOT NULL ,
                                 first_name           varchar(100)  NOT NULL ,
                                 last_name            varchar(100)  NOT NULL ,
                                 email                varchar(100)  NOT NULL ,
                                 passwd               varchar(100)  NOT NULL ,
                                 CONSTRAINT pk_users_id PRIMARY KEY ( id ),
                                 CONSTRAINT unq_user_email UNIQUE ( email ) ,
                                 CONSTRAINT unq_user_username UNIQUE ( username )
);

CREATE TABLE IF NOT EXISTS jwt_auth.users_roles (
                                       user_id              bigint  NOT NULL ,
                                       role_id              bigint  NOT NULL
);

ALTER TABLE jwt_auth.users_roles ADD CONSTRAINT fk_user_roles_user_id FOREIGN KEY ( user_id ) REFERENCES jwt_auth.users( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE jwt_auth.users_roles ADD CONSTRAINT fk_user_roles_role_id FOREIGN KEY ( role_id ) REFERENCES jwt_auth.roles( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

INSERT INTO jwt_auth.roles (role_name) values ('ROLE_ADMIN');
INSERT INTO jwt_auth.roles (role_name) values ('ROLE_USER');