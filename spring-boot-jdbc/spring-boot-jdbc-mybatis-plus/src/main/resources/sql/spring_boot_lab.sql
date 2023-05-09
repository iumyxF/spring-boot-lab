/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : spring_boot_lab

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/05/2023 14:25:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint NOT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (3001, '关卡菜单', '/test/level');
INSERT INTO `menu` VALUES (3002, '活动菜单', '/test/activity');
INSERT INTO `menu` VALUES (3003, '商店菜单', '/test/shop');
INSERT INTO `menu` VALUES (3004, '基建菜单', '/test/infrastructure');
INSERT INTO `menu` VALUES (3005, '背包菜单', '/test/backpack');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2001, '近卫');
INSERT INTO `role` VALUES (2002, '术士');
INSERT INTO `role` VALUES (2003, '特种');
INSERT INTO `role` VALUES (2004, '医疗');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (2001, 3001);
INSERT INTO `role_menu` VALUES (2001, 3002);
INSERT INTO `role_menu` VALUES (2001, 3003);
INSERT INTO `role_menu` VALUES (2001, 3004);
INSERT INTO `role_menu` VALUES (2001, 3005);
INSERT INTO `role_menu` VALUES (2002, 3001);
INSERT INTO `role_menu` VALUES (2002, 3002);
INSERT INTO `role_menu` VALUES (2002, 3003);
INSERT INTO `role_menu` VALUES (2003, 3003);
INSERT INTO `role_menu` VALUES (2003, 3004);
INSERT INTO `role_menu` VALUES (2003, 3005);
INSERT INTO `role_menu` VALUES (2004, 3003);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1001, 'jack', 20);
INSERT INTO `user` VALUES (1002, 'rookie', 23);
INSERT INTO `user` VALUES (1004, 'ie4', 89);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1001, 2001);
INSERT INTO `user_role` VALUES (1002, 2002);
INSERT INTO `user_role` VALUES (1003, 2004);

SET FOREIGN_KEY_CHECKS = 1;
