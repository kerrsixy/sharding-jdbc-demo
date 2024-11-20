SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS database1;

USE database1;

DROP TABLE IF EXISTS `t_user_1`;
DROP TABLE IF EXISTS `t_user_2`;
DROP TABLE IF EXISTS `t_dict`;
DROP TABLE IF EXISTS `t_user_item_1`;
DROP TABLE IF EXISTS `t_user_item_2`;

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
CREATE TABLE `t_user_1`
(
    `id`       bigint(20) NOT NULL COMMENT '用户编号',
    `name`     varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`      int(11)      DEFAULT NULL COMMENT '用户年龄',
    `salary`   double       DEFAULT NULL COMMENT '用户薪资',
    `birthday` datetime     DEFAULT NULL COMMENT '用户生日',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_user_2
-- ----------------------------
CREATE TABLE `t_user_2`
(
    `id`       bigint(20) NOT NULL COMMENT '用户编号',
    `name`     varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`      int(11)      DEFAULT NULL COMMENT '用户年龄',
    `salary`   double       DEFAULT NULL COMMENT '用户薪资',
    `birthday` datetime     DEFAULT NULL COMMENT '用户生日',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
CREATE TABLE `t_dict`
(
    `dict_id` bigint(20)  NOT NULL COMMENT '字典id',
    `type`    varchar(50) NOT NULL COMMENT '字典类型',
    `code`    varchar(50) NOT NULL COMMENT '字典编码',
    `value`   varchar(50) NOT NULL COMMENT '字典值',
    PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_user_item_1
-- ----------------------------
CREATE TABLE `t_user_item_1`
(
    `item_id` bigint(20) NOT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_user_item_2
-- ----------------------------
CREATE TABLE `t_user_item_2`
(
    `item_id` bigint(20) NOT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE DATABASE IF NOT EXISTS database2;

USE database2;

DROP TABLE IF EXISTS `t_user_1`;
DROP TABLE IF EXISTS `t_user_2`;
DROP TABLE IF EXISTS `t_dict`;
DROP TABLE IF EXISTS `t_user_item_1`;
DROP TABLE IF EXISTS `t_user_item_2`;

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
CREATE TABLE `t_user_1`
(
    `id`       bigint(20) NOT NULL COMMENT '用户编号',
    `name`     varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`      int(11)      DEFAULT NULL COMMENT '用户年龄',
    `salary`   double       DEFAULT NULL COMMENT '用户薪资',
    `birthday` datetime     DEFAULT NULL COMMENT '用户生日',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_user_2
-- ----------------------------
CREATE TABLE `t_user_2`
(
    `id`       bigint(20) NOT NULL COMMENT '用户编号',
    `name`     varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`      int(11)      DEFAULT NULL COMMENT '用户年龄',
    `salary`   double       DEFAULT NULL COMMENT '用户薪资',
    `birthday` datetime     DEFAULT NULL COMMENT '用户生日',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
CREATE TABLE `t_dict`
(
    `dict_id` bigint(20)  NOT NULL COMMENT '字典id',
    `type`    varchar(50) NOT NULL COMMENT '字典类型',
    `code`    varchar(50) NOT NULL COMMENT '字典编码',
    `value`   varchar(50) NOT NULL COMMENT '字典值',
    PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_user_item_1
-- ----------------------------
CREATE TABLE `t_user_item_1`
(
    `item_id` bigint(20) NOT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for t_user_item_2
-- ----------------------------
CREATE TABLE `t_user_item_2`
(
    `item_id` bigint(20) NOT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

