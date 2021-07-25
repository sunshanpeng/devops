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