CREATE TABLE publishings
(
    id         VARCHAR(36) NOT NULL,
    code       TEXT        NOT NULL,
    music      VARCHAR(36) NOT NULL,
    user       VARCHAR(36) NOT NULL,
    datetime   DATETIME    NOT NULL,
    PRIMARY KEY (id)
);
