SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          bigint(20) unsigned                              NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version`     int(11)                                          NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available`   int(11)                                          NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted`  int(11)                                          NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `username`    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`    varchar(127) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
    `oauth_pwd`   varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '授权密码',
    `nickname`    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户昵称',
    `sex`         int(11)                                          NOT NULL DEFAULT '0' COMMENT '性别【1：男，2：女，0：未知】',
    `phone`       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '邮箱',
    `avatar`      varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '头像',
    `token`       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT 'token',
    `type`        int(11)                                          NOT NULL DEFAULT '1' COMMENT '用户类型：1：游客，2：系统用户',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`),
    KEY `idx_email` (`email`),
    KEY `idx_phone` (`phone`),
    KEY `idx_token` (`token`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户表'



DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `id`          bigint(20)                                       NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`   varchar(30)                                      NOT NULL COMMENT '角色名称',
    `role_key`    varchar(100)                                     NOT NULL COMMENT '角色权限字符串',
    `role_sort`   int(4)                                           NOT NULL COMMENT '显示顺序',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version`     int(11)                                          NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available`   int(11)                                          NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted`  int(11)                                          NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_update_date` (`update_date`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='权限表';

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`
(
    `id`          bigint(20)                                       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint(20)                                       NOT NULL COMMENT '用户ID',
    `role_id`     bigint(20)                                       NOT NULL COMMENT '角色ID',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version`     int(11)                                          NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available`   int(11)                                          NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted`  int(11)                                          NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户和权限关联表';


DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`
(
    `id`             int(4)                                           NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50)                                      NOT NULL COMMENT '公告标题',
    `notice_type`    char(1)                                          NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` varchar(2000)                                             DEFAULT NULL COMMENT '公告内容',
    `status`         char(1)                                                   DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date`    datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date`    datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version`        int(11)                                          NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available`      int(11)                                          NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted`     int(11)                                          NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='通知公告表';




