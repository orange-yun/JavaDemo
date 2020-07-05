CREATE TABLE musics
(
    id          VARCHAR(36) NOT NULL,
    title       VARCHAR(50) NOT NULL,
    description TEXT        NOT NULL,
    releasedate DATETIME    NOT NULL,
    status      INT         NOT NULL,
    PRIMARY KEY (id)
);
