/*
Navicat MySQL Data Transfer

Source Server         : server
Source Server Version : 50710
Source Host           : 192.168.1.106:3306
Source Database       : food_safety

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-07-21 10:21:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(36) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `shiro_name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('49e2740f-4b2a-47fa-b22a-1a1606cad1ad', '检测员', 'checker', '1', '2016-01-15 10:41:37', '2016-04-21 17:26:42');
INSERT INTO `t_role` VALUES ('51475cf8-3c5a-458a-a55a-4e10bedca9c5', '导师', 'tutor', '1', '2016-06-30 10:47:26', null);
INSERT INTO `t_role` VALUES ('61808c74-6e9a-425b-8a4f-0d535ba3df6c', '访客', 'guest', '0', '2016-05-16 16:09:38', '2016-05-26 16:24:18');
INSERT INTO `t_role` VALUES ('82533bf3-9448-403c-880e-7577169a1e4a', '学生', 'student', '1', '2016-05-09 13:35:40', null);
INSERT INTO `t_role` VALUES ('a43419a9-cef0-444c-9edf-643dff455479', '管理员', 'admin', '1', '2016-01-19 12:17:55', '2016-01-19 12:17:55');
INSERT INTO `t_role` VALUES ('c42d72de-2adf-46da-8591-faddbd54e008', 'xiaoTest', 'xiao', '0', '2016-05-20 11:27:53', '2016-05-27 13:28:28');
INSERT INTO `t_role` VALUES ('c5560dcf-986e-4b17-9555-c8bb63243a36', '质量管理员', 'manager', '1', '2016-01-15 10:43:16', '2016-01-15 10:43:16');
INSERT INTO `t_role` VALUES ('d681216a-cf6e-41d9-9525-7c24ac628031', '111', '1', '0', '2016-06-29 11:58:07', '2016-06-29 11:58:23');
INSERT INTO `t_role` VALUES ('f0821759-40e1-4d5d-99de-744115393c1f', '测试09', 'test6', '0', '2016-04-18 11:31:56', '2016-04-18 11:31:56');
