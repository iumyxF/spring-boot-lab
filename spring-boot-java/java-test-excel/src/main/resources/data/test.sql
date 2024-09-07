#  如果存在表先删除
drop table if exists `user`;
# 建表语句
CREATE TABLE `user`
(
    `id`        int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`      varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工姓名',
    `phone_num` varchar(20) COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '联系方式',
    `address`   varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '住址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 生成测试数据
DELIMITER //
drop procedure IF EXISTS InsertTestData;
CREATE PROCEDURE InsertTestData()
BEGIN
    DECLARE counter INT DEFAULT 1;

    WHILE counter < 1000000
        DO
            INSERT INTO user (id, name, phone_num, address)
            VALUES (counter, CONCAT('name_', counter), CONCAT('phone_', counter), CONCAT('add_', counter));
            SET counter = counter + 1;
        END WHILE;
END //
DELIMITER;

-- 调用存储过程插入数据
CALL InsertTestData();