-- # 用于用户数据库及表的初始化
DROP DATABASE if exists `db_test`;
CREATE DATABASE if not exists `db_test`;

USE `db_test`;

-- # 用户表: id、名称、密码、手机号、身份证号、账户余额
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `phone` varchar(15),
  `id_card` varchar(16),
  `balance` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- # 店铺表：id、名称、类别、描述
CREATE TABLE IF NOT EXISTS `store` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `type` int(4) NOT NULL,
    `description` varchar(10240),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- # 运输状态表：id、商品id、购买价格、购买数量、订单id
CREATE TABLE IF NOT EXISTS `shipping` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `status` int(11) NOT NULL,
    `weight` int(11) NOT NULL,
    `shipping_price` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- # 订单表:id、用户id、订单状态、、总价、生成时间、更新时间
CREATE TABLE IF NOT EXISTS `customer_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `order_status` int(1) NOT NULL,
    `total_amount` int(11) NOT NULL,
    `shipping_id` int(11) NOT NULL,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    foreign key (user_id) references user(id),
    foreign key (shipping_id) references shipping(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- # 订单商品表：id、商品id、购买价格、购买数量、订单id
CREATE TABLE IF NOT EXISTS `order_item` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `item_id` int(11) NOT NULL,
    `item_price` int(11) NOT NULL,
    `quantity` int(11) NOT NULL,
    `order_id` int(11) NOT NULL,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    foreign key (order_id) references customer_order(id),
    foreign key (item_id) references item(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;