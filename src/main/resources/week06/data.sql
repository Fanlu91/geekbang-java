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