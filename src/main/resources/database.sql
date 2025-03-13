CREATE TABLE users
(
    id       UUID DEFAULT gen_random_uuid(),
    names    VARCHAR(100) NOT NULL,
    phone    VARCHAR(10)  NOT NULL,
    username VARCHAR(50)  NOT NULL,

    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_username_uindex UNIQUE (username),
    CONSTRAINT users_phone_uindex UNIQUE (phone)
);

CREATE TYPE ROLE AS ENUM ('JEFE', 'TECNICO', 'VENDEDOR', 'DESARROLLADOR');

CREATE TABLE identity.user_accounts
(
    id       UUID DEFAULT gen_random_uuid(),
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    role     ROLE         NOT NULL,

    CONSTRAINT user_accounts_pk PRIMARY KEY (id),
    CONSTRAINT user_accounts_username_uindex UNIQUE (username)
);


INSERT INTO identity.user_accounts (id, username, password, role)
VALUES ('4e53d392-131c-4fb6-a8cc-8042690ade40', 'ecajape',
        '{bcrypt}$2a$10$zI28wLm8NBjNNPAVfArGNOaoe3.62PR0skRdn2Q/hWse3IWHwlJv6', 'JEFE');
INSERT INTO identity.user_accounts (id, username, password, role)
VALUES ('7e798726-d261-41ba-8e47-bb15475da84b', 'vfred0',
        '{bcrypt}$2a$10$NhmBBan0W9wjz9V28z0YJ.4YKOgZZY65njcBARPcFJSQOJpTeKsSy', 'DESARROLLADOR');
