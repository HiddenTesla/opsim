-- No auto execution. Just for recording.

CREATE  TABLE  IF NOT EXISTS `artifact` (
    `artifact_id`      INT NOT NULL AUTO_INCREMENT,
    `rarity`           INT,
    `level`            INT,
    `artifact_type`    VARCHAR(128),
    `main_stat_type`   VARCHAR(256),
    `main_stat_value`  FLOAT(32),
    PRIMARY KEY(`artifact_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE  TABLE  IF NOT EXISTS `sub_stat` (
    `sub_stat_id`      INT NOT NULL AUTO_INCREMENT,
    `artifact_id`      INT,
    `sub_stat_type`    VARCHAR(256),
    `sub_stat_value`  FLOAT(32),
    PRIMARY KEY(`sub_stat_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE  TABLE  IF NOT EXISTS `user` (
    `uid`      INT NOT NULL AUTO_INCREMENT,
    `username`       VARCHAR(256),
    `password_sha1`  VARCHAR(256),
    `salt`           VARCHAR(256),
    CONSTRAINT `Unique_username`  UNIQUE(`username`),
    PRIMARY KEY(`uid`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;
