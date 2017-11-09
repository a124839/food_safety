/*
Navicat MySQL Data Transfer

Source Server         : server
Source Server Version : 50710
Source Host           : 192.168.1.106:3306
Source Database       : food_safety

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-07-21 10:21:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_algorithm
-- ----------------------------
DROP TABLE IF EXISTS `t_algorithm`;
CREATE TABLE `t_algorithm` (
  `id` varchar(36) NOT NULL,
  `attachment_id` varchar(36) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '1 预处理算法 2 评价算法',
  `category` int(11) DEFAULT '2' COMMENT '1 系统预定义算法 2 自定义算法',
  `sample_code` text COMMENT '示例代码',
  `example` text COMMENT '实例',
  `memo` text,
  `status` tinyint(4) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_algorithm_params
-- ----------------------------
DROP TABLE IF EXISTS `t_algorithm_params`;
CREATE TABLE `t_algorithm_params` (
  `id` varchar(36) NOT NULL,
  `algorithm_id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_analysis
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis`;
CREATE TABLE `t_analysis` (
  `id` varchar(36) NOT NULL,
  `project_id` varchar(36) DEFAULT NULL,
  `algorithm_info` varchar(255) DEFAULT NULL COMMENT '算法 json数组{"algorithmId":"xxxxx",{"params":}}',
  `datas_info` longtext COMMENT '分析后的数据信息json数组，{id:data}',
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` varchar(36) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '文件保存位置',
  `file_size` decimal(10,0) DEFAULT NULL COMMENT '文件大小（KB）',
  `file_original_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL COMMENT '文件类型（后缀）',
  `uploader` varchar(255) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `jmyps` varchar(255) DEFAULT NULL COMMENT '建模样品数',
  `mxzcfs` varchar(255) DEFAULT NULL COMMENT '模型主成分数',
  `rmscv` varchar(255) DEFAULT NULL COMMENT '交叉验证均方根误差',
  `r_r` varchar(255) DEFAULT NULL COMMENT '决定系数',
  `memo` varchar(255) DEFAULT NULL,
  `analysis_id` varchar(36) DEFAULT NULL,
  `modal_attachment_id` varchar(36) DEFAULT NULL,
  `data_attachment_id` varchar(36) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL COMMENT '评价人',
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_datas
-- ----------------------------
DROP TABLE IF EXISTS `t_datas`;
CREATE TABLE `t_datas` (
  `id` varchar(36) NOT NULL,
  `instrument_id` varchar(36) DEFAULT NULL COMMENT '仪器ID',
  `sample_id` varchar(36) DEFAULT NULL,
  `project_id` varchar(36) DEFAULT NULL,
  `attachment_id` varchar(36) DEFAULT NULL,
  `resolution` varchar(255) DEFAULT NULL COMMENT '分辨率',
  `wavelength_range` varchar(255) DEFAULT NULL COMMENT '波长范围',
  `scanning_times` int(11) DEFAULT NULL COMMENT '扫描次数',
  `scanning_duration` varchar(255) DEFAULT NULL COMMENT '扫描时长',
  `data` mediumtext COMMENT 'json数组格式',
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_instrument
-- ----------------------------
DROP TABLE IF EXISTS `t_instrument`;
CREATE TABLE `t_instrument` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL COMMENT '型号',
  `category` varchar(36) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `sn` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '资产编号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `price` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL COMMENT '购买日期',
  `install_end_date` date DEFAULT NULL,
  `install_start_date` date DEFAULT NULL,
  `y` varchar(255) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `manufactor` varchar(255) DEFAULT NULL COMMENT '厂家',
  `performances` text COMMENT '性能指标',
  `attachment_id` varchar(36) DEFAULT NULL,
  `memo` text COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '1： 正常； 0： 停用',
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_instrument_service
-- ----------------------------
DROP TABLE IF EXISTS `t_instrument_service`;
CREATE TABLE `t_instrument_service` (
  `id` varchar(36) NOT NULL,
  `instrument_id` varchar(36) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `service_date` datetime DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `schema_attachment_id` varchar(36) DEFAULT NULL COMMENT '实验方案附件id',
  `standard_attachment_id` varchar(36) DEFAULT NULL COMMENT '实验标准附件',
  `operator` varchar(36) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0：已结束；1：实验中',
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id` varchar(36) NOT NULL,
  `project_id` varchar(36) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `ct` date DEFAULT NULL,
  `ut` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for t_role_function_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function_mapping`;
CREATE TABLE `t_role_function_mapping` (
  `id` varchar(36) NOT NULL,
  `function` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_sample
-- ----------------------------
DROP TABLE IF EXISTS `t_sample`;
CREATE TABLE `t_sample` (
  `id` varchar(36) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_lv1_id` varchar(36) DEFAULT NULL,
  `category_lv2_id` varchar(36) DEFAULT NULL,
  `producing_area` varchar(255) DEFAULT NULL COMMENT '产地',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `manufactor` varchar(255) DEFAULT NULL,
  `batches` varchar(255) DEFAULT NULL COMMENT '批次',
  `memo` varchar(255) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_index` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_sample_indicator
-- ----------------------------
DROP TABLE IF EXISTS `t_sample_indicator`;
CREATE TABLE `t_sample_indicator` (
  `id` varchar(36) NOT NULL,
  `sample_id` varchar(36) DEFAULT NULL,
  `indicator_name` varchar(255) DEFAULT NULL COMMENT '指标名称',
  `indicator_value` varchar(255) DEFAULT NULL COMMENT '指标值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_standards
-- ----------------------------
DROP TABLE IF EXISTS `t_standards`;
CREATE TABLE `t_standards` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型 ',
  `summary` varchar(255) DEFAULT NULL COMMENT '概述',
  `issuance_department` varchar(255) DEFAULT NULL,
  `release_time` date DEFAULT NULL COMMENT '发布时间',
  `period` int(11) DEFAULT NULL COMMENT '使用年限',
  `attachment_id` varchar(36) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 正常 2 过期',
  `memo` text,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` varchar(36) NOT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` text,
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(36) NOT NULL,
  `login_account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `tutor` varchar(255) DEFAULT NULL,
  `graduated_school` varchar(255) DEFAULT NULL,
  `research_direction` varchar(255) DEFAULT NULL,
  `stu_code` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '2' COMMENT '0:已禁用 1:正常 2:待审核 3:审核不通过',
  `ct` datetime NOT NULL,
  `ut` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_account` (`login_account`,`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_mapping`;
CREATE TABLE `t_user_role_mapping` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
