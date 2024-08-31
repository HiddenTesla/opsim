-- No auto execution. Just for recording.

CREATE  TABLE  IF NOT EXISTS `artifact` (
    `artifact_id`      INT NOT NULL AUTO_INCREMENT,
    `rarity`           INT,
    `level`            INT,
    `artifact_type`    VARCHAR(128),
    `main_stat_type`   VARCHAR(256),
    `main_stat_value`  FLOAT(32),
    `create_date`      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`artifact_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE  TABLE  IF NOT EXISTS `sub_stat` (
    `sub_stat_id`      INT NOT NULL AUTO_INCREMENT,
    `artifact_id`      INT,
    `sub_stat_type`    VARCHAR(256),
    `sub_stat_value`   FLOAT(32),
    `create_date`      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`sub_stat_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE  TABLE  IF NOT EXISTS `rewind_sub_stat` (
    `sub_stat_id`      INT NOT NULL AUTO_INCREMENT,
    `artifact_id`      INT,
    `sub_stat_type`    VARCHAR(256),
    `sub_stat_value`   FLOAT(32),
    `create_date`      DATETIME,
    `rewind_date`      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`sub_stat_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE  TABLE  IF NOT EXISTS `game_ranking` (
  `rank_id`   INT NOT NULL AUTO_INCREMENT,
  `name`      VARCHAR(256),
  `year`      INT(32),
  `note`      VARCHAR(1024),
  `publisher` VARCHAR(256),
  `rank`      INT(16),
  `pros`      TEXT,
  `cons`      TEXT,
  PRIMARY KEY(`rank_id`)
  )
  ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;
