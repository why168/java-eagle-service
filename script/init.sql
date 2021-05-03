use test;

CREATE TABLE `user`(
    `id`   int(11)     NOT NULL AUTO_INCREMENT,
    `name` varchar(32) NOT NULL DEFAULT '',
    `age`  smallint(6)          DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `msg_notify_xxx` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `msg_id` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息唯一id',
    `uid` bigint(20) unsigned NOT NULL COMMENT '通知人',
    `role` tinyint(1) NOT NULL COMMENT '0用户;1商户;',
    `msg_type` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息类型',
    `read_status` tinyint(1) DEFAULT '0' COMMENT '0未读,1已读',
    `msg_entity` varchar(4096) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '消息体json',
    `insert_time` bigint(20) unsigned NOT NULL COMMENT '分页时间戳',
    `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删；1已删',
    `extra` varchar(4096) CHARACTER SET utf8mb4 DEFAULT NULL,
    `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_msg_id` (`msg_id`),
    KEY `idx_combined` (`uid`,`role`,`msg_type`,`del`,`insert_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知中心';

CREATE TABLE `read_record_00` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `uid` bigint(20) unsigned NOT NULL COMMENT '通知人',
    `role` tinyint(1) NOT NULL COMMENT '0用户;1商户;',
    `msg_type` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息类型',
    `read_time` bigint(20) unsigned NOT NULL COMMENT '已读时间',
    `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_uid_role` (`uid`,`role`,`msg_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已读消息记录'