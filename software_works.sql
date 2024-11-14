/*
 Navicat Premium Data Transfer

 Source Server         : potato
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Schema         : software_works

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 17/12/2023 22:34:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `group_id` int(0) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `notice` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `createor_id` int(0) NOT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_notifications
-- ----------------------------
DROP TABLE IF EXISTS `group_notifications`;
CREATE TABLE `group_notifications`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `receiver_user_id` int(0) NOT NULL COMMENT '接收者的用户id',
  `sender_user_id` int(0) NOT NULL COMMENT '发送者的用户id',
  `type` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表示这个消息是干什么的\\\\info表示提示信息\\\\request表示加群请求',
  `request_status` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表示request发送过去是否被接受,三个状态:unknown/accept/accept',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述信息,如:XXX申请加入XXX/XXX退出了XXX',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '这条信息创建的时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '这条消息更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `msg_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `group_id` int(0) NOT NULL COMMENT '消息属于哪个组',
  `user_id` int(0) NOT NULL COMMENT '发送者的id',
  `type` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分为图片、文字和表情,如果不为文字那么content字段该为url',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '消息的内容',
  `usability` int(0) NOT NULL DEFAULT 1 COMMENT '可用性,1可用,0不可显示',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '信息发来的时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `query_msg`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名,用于标识用户的唯一标识',
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `cookie` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `head_sculpture_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像的地址',
  `self_introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户的个人简介',
  `lastlogin_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录的时间',
  `usability` int(0) NOT NULL DEFAULT 1 COMMENT '0->被删除\\\\\r\n1->可用\\\\\r\n2->被禁言\\\\',
  PRIMARY KEY (`user_id`, `username`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `user_id` int(0) NOT NULL,
  `group_id` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
