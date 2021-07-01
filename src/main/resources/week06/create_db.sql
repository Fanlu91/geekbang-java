-- # 用于用户数据库及表的初始化
DROP DATABASE if exists `db_test`;
CREATE DATABASE if not exists `db_test`;

USE `db_test`;

-- # 用户表: id、名称、密码、手机号、身份证号、账户余额
CREATE TABLE IF NOT EXISTS `t_user` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) NOT NULL DEFAULT 'None',
  `f_password` varchar(64) NOT NULL DEFAULT 'password',
  `f_phone` varchar(15),
  `f_id_card` char(19),
  `f_balance` int(11) NOT NULL DEFAULT 0,
  `f_state` tinyint(11) NOT NULL DEFAULT 0,
  `f_create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `f_update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- # 店铺表：id、名称、类别、描述
CREATE TABLE IF NOT EXISTS `t_store` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `type` int(4) NOT NULL,
    `description` varchar(10240),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='店铺信息';

-- # 商品表：id、名称、描述、当前价格、所属店铺id、商品库存
CREATE TABLE IF NOT EXISTS `item` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(128) NOT NULL,
    `description` varchar(10240),
    `current_price` int(11) NOT NULL,
    `store_id` int(11) NOT NULL,
    `inventory` int(11) NOT NULL DEFAULT 0, 
    PRIMARY KEY (`id`),
    foreign key (store_id) references store(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息';

-- # 运输状态表：id、商品id、购买价格、购买数量、订单id
CREATE TABLE IF NOT EXISTS `t_shipping` (
    `f_id` int(11) NOT NULL AUTO_INCREMENT,
    `f_status` tinyint(1) NOT NULL,
    `f_weight` int(11) NOT NULL,
    `f_shipping_price` int(11) NOT NULL,
    PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='物流信息';

-- # 订单表:id、用户id、订单状态、、总价、生成时间、更新时间
CREATE TABLE IF NOT EXISTS `t_my_order` (
    `f_id` BIGINT NOT NULL AUTO_INCREMENT,
    `f_user_id` int(11) NOT NULL DEFAULT 0,
    `f_order_status` tinyint(1) NOT NULL DEFAULT 0,
    `f_total_amount` int(11) NOT NULL DEFAULT 0,
    `f_shipping_id` int(11) NOT NULL DEFAULT 0,
    `f_create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `f_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`f_id`)
    -- foreign key (f_user_id) references t_user(f_id),
    -- foreign key (f_shipping_id) references t_shipping(f_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单信息 2库 16表';

-- # 订单商品表：id、商品id、购买价格、购买数量、订单id
CREATE TABLE IF NOT EXISTS `t_order_item` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `item_id` int(11) NOT NULL,
    `item_price` int(11) NOT NULL,
    `quantity` int(11) NOT NULL,
    `order_id` BIGINT NOT NULL,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    foreign key (order_id) references t_my_order(id),
    foreign key (item_id) references item(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单物品信息';