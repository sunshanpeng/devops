DROP TABLE IF EXISTS t_organization;
CREATE TABLE `t_organization`
(
    `id`          bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '组织名称',
    `parent_id`   bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级组织',
    `create_time` datetime(0) NOT NULL DEFAULT now(),
    `update_time` datetime(0) NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP (0),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_name`(`name`) USING BTREE
);

INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (1, '产品研发部', 0, '2021-07-25 18:27:57', '2021-07-25 18:39:41');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (2, '前端组', 1, '2021-07-25 18:29:24', '2021-07-25 18:29:24');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (3, '订单组', 1, '2021-07-25 18:29:44', '2021-07-25 18:29:44');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (4, '会员组', 1, '2021-07-25 18:29:52', '2021-07-25 18:29:52');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (5, '供应链组', 1, '2021-07-25 18:29:57', '2021-07-25 18:29:57');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (6, '质量保障组', 1, '2021-07-25 18:30:12', '2021-07-25 18:30:12');
INSERT INTO `t_organization`(`id`, `name`, `parent_id`, `create_time`, `update_time`)
VALUES (7, '运维组', 1, '2021-07-25 18:30:23', '2021-07-25 18:30:23');

DROP TABLE IF EXISTS t_member;
CREATE TABLE `t_member`
(
    `id`          bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
    `full_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '全名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
    `phone`       bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '手机号',
    `is_deleted`  tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0),
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_username`(`username`) USING BTREE,
    UNIQUE INDEX `uk_phone`(`phone`) USING BTREE,
    UNIQUE INDEX `uk_email`(`email`) USING BTREE
);