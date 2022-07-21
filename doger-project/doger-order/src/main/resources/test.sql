create table test.product_order
(
    id            int auto_increment comment '主键id'
        primary key,
    order_no      varchar(64)                           not null comment '订单编号',
    create_time   timestamp default current_timestamp() not null on update current_timestamp() comment '创建时间',
    update_time   timestamp                             null comment '更新时间',
    version       int       default 0                   not null comment '版本号',
    amount        decimal                               not null comment '实付金额',
    trans_time    timestamp                             null comment '交易时间',
    product_no    varchar(64)                           null comment '产品编号',
    product_name  varchar(128)                          null comment '产品名称',
    product_price decimal                               null comment '产品价格',
    product_count decimal                               null comment '产品数量',
    discount      decimal                               null comment '折扣',
    consumer_no   varchar(64)                           null comment '顾客编号',
    origin_amount decimal                               null comment '原始金额',
    constraint table_name_order_no_uindex
        unique (order_no)
);
