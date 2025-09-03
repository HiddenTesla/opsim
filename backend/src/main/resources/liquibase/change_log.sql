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

CREATE TABLE IF NOT EXISTS `domain_character` (
  `character_id` INT NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(64) NOT NULL,
  `rarity`       INT NOT NULL,
  `element_type` VARCHAR(64) NOT NULL,
  `weapon_type`  VARCHAR(64) NOT NULL,
  PRIMARY KEY(`character_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;
