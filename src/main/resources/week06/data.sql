insert into t_user (f_name,f_password,f_phone,f_id_card,f_balance,f_state)  values("a","a","a","1234567890123456789",1,1);


DROP PROCEDURE IF EXISTS order_init_1m;
DELIMITER $
CREATE PROCEDURE order_init_1m()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i<=10 DO
            insert into db_test.shipping (status, weight, shipping_price)
            VALUES (CEILING(rand()*10),CEILING(rand()),CEILING(rand()*100));
        SET i = i+1;
    END WHILE;
    commit;
END $
CALL orders_initData();

CREATE TABLE IF NOT EXISTS `t_customer_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `f_user_id` int(11) NOT NULL,
    `f_order_status` tinyint(1) NOT NULL,
    `f_total_amount` int(11) NOT NULL,
    `f_shipping_id` int(11) NOT NULL,
    `f_create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `f_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    foreign key (f_user_id) references t_user(f_id),
    foreign key (f_shipping_id) references t_shipping(f_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


insert into t_customer_order
    (f_user_id,f_order_status,f_total_amount,f_shipping_id)
    values
    (1,CEILING(rand()*5,CEILING(rand()*300,1)