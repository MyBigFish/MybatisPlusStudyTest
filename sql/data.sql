use mp;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    `id` BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
    `name` VARCHAR(30) not NULL DEFAULT '' COMMENT '姓名',
    `age` INT(11) not null DEFAULT 0 NULL COMMENT '年龄',
    `email` VARCHAR(50) not NULL DEFAULT '' COMMENT '邮箱',
    `create_time` datetime not null default CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `remark` json default null comment '备注',
    `is_delete` bigint(20) not null default 0 comment '是否删除',
    PRIMARY KEY (id),
    UNIQUE KEY `uniq_email_is_delete` (`email`, `is_delete`)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8mb4 COMMENT ='用户';
#
#
# DELETE FROM user;
#
# INSERT INTO user (`name`, `age`, `email`) VALUES
#                                             ('Jone', 18, 'test1@baomidou.com'),
#                                             ('Jack', 20, 'test2@baomidou.com'),
#                                             ('Tom', 28, 'test3@baomidou.com'),
#                                             ('Sandy', 21, 'test4@baomidou.com'),
#                                             ('Billie', 24, 'test5@baomidou.com');
