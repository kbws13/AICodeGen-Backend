create database if not exists ai_code_gen;

use ai_code_gen;

create table if not exists user
(
    id            bigint auto_increment primary key comment 'id',
    account       varchar(256)                           not null comment '账号',
    password      varchar(256)                           not null comment '密码',
    name          varchar(256)                           null comment '用户昵称',
    avatar        varchar(1024)                          null comment '用户头像',
    profile       varchar(512)                           null comment '个人简介',
    role          varchar(256) default 'user'            not null comment '用户角色: user/admin/ban',
    editTime      datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint      default 0                 not null comment '是否删除',
    vipExpireTime datetime                               null comment '会员过期时间',
    vipCode       varchar(128)                           null comment '会员兑换码',
    vipNumber     bigint                                 null comment '会员编号',
    shareCode     varchar(20)  DEFAULT NULL COMMENT '分享码',
    inviteUser    bigint       DEFAULT NULL COMMENT '邀请用户 id',


    unique key uk_account (account),
    index idx_name (name)
) comment '用户表';