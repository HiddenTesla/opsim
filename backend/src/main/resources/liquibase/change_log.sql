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

CREATE TABLE IF NOT EXISTS `character` (
    `character_id`      INT NOT NULL AUTO_INCREMENT,
    `character_name`    VARCHAR(256),
    `rarity`            INT,
    `weapon_type`       VARCHAR(256),
    `element_type`      VARCHAR(256),
    PRIMARY KEY(`character_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

CREATE TABLE IF NOT EXISTS `gacha` (
    `gacha_id`  INT NOT NULL AUTO_INCREMENT,
    `rarity`    INT,
    `type`      VARCHAR(256),
    `item_id`   INT,
    `gacha_date` DATETIME,
    PRIMARY KEY(`gacha_id`)
)
ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Qiqi", "5", "Swords", "Cryo");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Keqing", "5", "Swords", "Electro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Mona", "5", "Catalysts", "Hydro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Jean", "5", "Swords", "Anemo");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Diluc", "5", "Claymores", "Pyro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Amber", "4", "Bows", "Pyro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Babara", "4", "Catalysts", "Hydro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Kaeya", "4", "Swords", "Pyro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Lisa", "4", "Catalysts", "Electro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Yoimiya", "5", "Bows", "Pyro");

INSERT INTO `character` (`character_name`, `rarity`, `weapon_type`, `element_type`)
VALUES ("Yae Miko", "5", "Catalysts", "Electro");
