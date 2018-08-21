/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : leechdb

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-05-04 15:27:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_area_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_area_info`;
CREATE TABLE `sys_area_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_area_uid` varchar(40) DEFAULT NULL COMMENT '唯一键，关联键',
  `sys_area_name` varchar(60) DEFAULT NULL COMMENT '地区名字',
  `sys_area_level` int(4) DEFAULT NULL COMMENT '级别-0:国家，根1：省2：市3：县(区)4：镇',
  `sys_parent_uid` varchar(40) DEFAULT NULL COMMENT '父节点uid',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  `del_status` bit(1) DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_area_info
-- ----------------------------
INSERT INTO `sys_area_info` VALUES ('2', '14885078885306566', '中国', '0', '', '2017-03-03 10:24:48', '2017-03-03 10:24:48', '', '\0');
INSERT INTO `sys_area_info` VALUES ('3', '14885221738734530', '湖北省', '1', '14885078885306566', '2017-03-03 14:22:53', '2017-03-03 14:53:08', '', '\0');
INSERT INTO `sys_area_info` VALUES ('4', '14885239004348861', '孝感市', '2', '14885221738734530', '2017-03-03 14:51:40', '2017-03-03 14:53:14', '', '\0');
INSERT INTO `sys_area_info` VALUES ('5', '14885360838006300', '汉川', '3', '14885239004348861', '2017-03-03 18:14:43', '2017-03-03 18:14:51', '', '\0');
INSERT INTO `sys_area_info` VALUES ('6', '14885360838006378', '马口', '4', '14885360838006300', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('7', '14885360838006574', '庙头', '4', '14885360838006300', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('8', '14885239004348632', '应城', '3', '14885239004348861', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('9', '14885239004340121', '武汉市', '2', '14885221738734530', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('10', '14885221738734568', '广东省', '1', '14885078885306566', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('11', '14885078885306111', '美国', '0', ' ', null, null, null, '\0');
INSERT INTO `sys_area_info` VALUES ('12', '14888806079269864', '湖南', '1', '14885078885306566', '2017-03-07 17:56:47', '2017-03-09 16:06:07', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('13', '14888853983341247', '俄罗斯', '0', '', '2017-03-07 19:16:38', '2017-03-09 16:04:39', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('26', '14889541212772056', '长沙', '2', '14888806079269864', '2017-03-08 14:22:01', '2017-03-08 14:22:01', '01139932', '\0');
INSERT INTO `sys_area_info` VALUES ('34', '14890462868222018', '华盛顿州', '1', '14885078885306111', '2017-03-09 15:58:06', '2017-03-09 16:05:11', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('35', '14890463046265956', '华盛顿', '2', '14890462868222018', '2017-03-09 15:58:24', '2017-03-09 16:05:18', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('36', '14890463127691854', '莫斯科', '1', '14888853983341247', '2017-03-09 15:58:32', '2017-03-09 16:04:49', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('37', '14890467447664706', '武昌', '3', '14885239004340121', '2017-03-09 16:05:44', '2017-03-09 16:05:44', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('38', '14890467948077872', '娄底', '2', '14888806079269864', '2017-03-09 16:06:34', '2017-03-09 16:06:34', '123', '\0');
INSERT INTO `sys_area_info` VALUES ('41', '14891378517358927', '武汉撒', '2', '14885221738734530', '2017-03-10 17:24:11', '2017-03-10 17:24:11', 'admin', '');
INSERT INTO `sys_area_info` VALUES ('42', '14891384622374169', 'sdfsdf', '2', '14885221738734530', '2017-03-10 17:34:22', '2017-03-10 17:34:22', 'admin', '');
INSERT INTO `sys_area_info` VALUES ('43', '14891385102545017', 'gdfg', '3', '14891384622374169', '2017-03-10 17:35:10', '2017-03-10 17:35:10', 'admin', '');
INSERT INTO `sys_area_info` VALUES ('44', '14891386087063418', '打sdasd', '3', '14891384622374169', '2017-03-10 17:36:48', '2017-03-10 17:36:48', 'admin', '');
INSERT INTO `sys_area_info` VALUES ('45', '14891397159648474', 'hjghjghj', '0', '', '2017-03-10 17:55:15', '2017-03-10 17:55:15', '01139932', '');
INSERT INTO `sys_area_info` VALUES ('46', '14891397212281643', 'hjghjghj', '1', '14891397159648474', '2017-03-10 17:55:21', '2017-03-10 17:55:21', '01139932', '');

-- ----------------------------
-- Table structure for sys_oprt_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_oprt_table`;
CREATE TABLE `sys_oprt_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_table_uid` varchar(40) DEFAULT NULL COMMENT '操作表uid',
  `sys_table_ename` varchar(80) DEFAULT NULL COMMENT '英文表名',
  `sys_table_cname` varchar(40) DEFAULT NULL COMMENT '中文表名',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  `del_status` bit(1) DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  KEY `sys_table_uid` (`sys_table_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_oprt_table
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_info`;
CREATE TABLE `sys_resource_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_resource_uid` varchar(40) DEFAULT NULL COMMENT '唯一键，关联键',
  `sys_resource_name` varchar(40) DEFAULT NULL COMMENT '资源节点名字',
  `sys_resource_level` int(4) DEFAULT NULL COMMENT '资源级别',
  `sys_resource_path` varchar(128) DEFAULT NULL COMMENT '资源操作url',
  `sys_parent_uid` varchar(40) DEFAULT NULL COMMENT '父节点uid',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  `del_status` bit(1) DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  KEY `sys_resource_uid` (`sys_resource_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_info
-- ----------------------------
INSERT INTO `sys_resource_info` VALUES ('1', '14862759478049741', '设备类型管理', '2', '/pages/demo', '14862759478049744', '1', '2017-03-05 16:05:25', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('2', '14862759478049742', '设备区域管理', '2', '/pages/demo2', '14862759478049744', '1', '2017-03-05 16:05:39', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('3', '14862759478049743', '设备可行性分析', '2', '/pages/demo3', '14862759478049744', '1', '2017-03-05 16:06:06', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('4', '14862759478049744', '设备管理', '1', '', '', '1', '2017-03-05 16:05:13', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('5', '14862759478049745', '人员管理', '2', '/pages/user', '14862759478049747', '1', '2017-03-05 16:06:35', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('6', '14862759478049746', '角色管理', '2', '/pages/role', '14862759478049747', '1', '2017-03-05 16:06:52', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('7', '14862759478049747', '系统管理', '1', '', ' ', '1', '2017-03-05 16:06:20', '3', '\0');
INSERT INTO `sys_resource_info` VALUES ('18', '14887012437361812', '资源管理', '2', '/pages/resource', '14862759478049747', '2017-03-05 16:07:23', '2017-05-04 11:07:59', '01139943', '\0');
INSERT INTO `sys_resource_info` VALUES ('19', '14887012777071188', '表格管理', '2', '/pages/operateTable', '14862759478049747', '2017-03-05 16:07:57', '2017-03-06 14:19:33', null, '\0');
INSERT INTO `sys_resource_info` VALUES ('24', '14888881262858705', '其他管理', '1', '', '', '2017-03-07 20:02:06', '2017-03-07 20:02:06', null, '\0');
INSERT INTO `sys_resource_info` VALUES ('25', '14888881635729582', '地域管理', '2', '/pages/area', '14888881262858705', '2017-03-07 20:02:43', '2017-03-07 20:02:43', null, '\0');

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_role_uid` varchar(40) DEFAULT NULL COMMENT '唯一键，关联键',
  `sys_role_name` varchar(40) DEFAULT NULL COMMENT '角色名字',
  `sys_role_desc` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  `del_status` bit(1) DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  KEY `sys_role_uid` (`sys_role_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
INSERT INTO `sys_role_info` VALUES ('2', '14882759478049742', '系统管理员', '管理权限', '2', '2017-05-02 15:13:59', '01139943', '\0');
INSERT INTO `sys_role_info` VALUES ('10', '14938677363549164', '设备管理用户', '部分权限', '2017-05-04 11:15:36', '2017-05-04 11:15:36', '000000', '\0');
INSERT INTO `sys_role_info` VALUES ('11', '14938677514102932', '普通用户', '普通用户', '2017-05-04 11:15:51', '2017-05-04 11:15:51', '000000', '\0');

-- ----------------------------
-- Table structure for sys_role_oprt
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_oprt`;
CREATE TABLE `sys_role_oprt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_role_uid` varchar(40) DEFAULT NULL COMMENT '角色表uid',
  `sys_table_uid` varchar(40) DEFAULT NULL COMMENT '操作表uid',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  PRIMARY KEY (`id`),
  KEY `fk_role_table_uid1` (`sys_role_uid`) USING BTREE,
  KEY `fk_role_table_uid2` (`sys_table_uid`) USING BTREE,
  CONSTRAINT `fk_role_table_uid1` FOREIGN KEY (`sys_role_uid`) REFERENCES `sys_role_info` (`sys_role_uid`),
  CONSTRAINT `fk_role_table_uid2` FOREIGN KEY (`sys_table_uid`) REFERENCES `sys_oprt_table` (`sys_table_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_oprt
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_role_uid` varchar(40) DEFAULT NULL COMMENT '用户表uid',
  `sys_resource_uid` varchar(40) DEFAULT NULL COMMENT '资源表uid',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  PRIMARY KEY (`id`),
  KEY `fk_role_resource1` (`sys_role_uid`),
  KEY `fk_role_resource2` (`sys_resource_uid`),
  CONSTRAINT `fk_role_resource1` FOREIGN KEY (`sys_role_uid`) REFERENCES `sys_role_info` (`sys_role_uid`),
  CONSTRAINT `fk_role_resource2` FOREIGN KEY (`sys_resource_uid`) REFERENCES `sys_resource_info` (`sys_resource_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('429', '14882759478049742', '14862759478049744', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('430', '14882759478049742', '14862759478049741', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('431', '14882759478049742', '14862759478049742', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('432', '14882759478049742', '14862759478049743', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('433', '14882759478049742', '14862759478049747', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('434', '14882759478049742', '14862759478049745', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('435', '14882759478049742', '14862759478049746', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('436', '14882759478049742', '14887012437361812', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('437', '14882759478049742', '14887012777071188', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('438', '14882759478049742', '14888881262858705', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('439', '14882759478049742', '14888881635729582', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('440', '14882759478049742', '14937756177096962', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('445', '14938677363549164', '14862759478049744', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('446', '14938677363549164', '14862759478049741', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('447', '14938677363549164', '14862759478049742', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('448', '14938677363549164', '14862759478049743', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('449', '14938677363549164', '14888881262858705', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('450', '14938677363549164', '14888881635729582', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('451', '14938677514102932', '14888881262858705', null, null, null);
INSERT INTO `sys_role_resource` VALUES ('452', '14938677514102932', '14888881635729582', null, null, null);

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_user_uid` varchar(40) DEFAULT NULL COMMENT '唯一键，关联键',
  `sys_username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `sys_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `sys_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sys_birth` varchar(20) DEFAULT NULL COMMENT '出生年月',
  `sys_sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `sys_cert_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `sys_cert_val` varchar(30) DEFAULT NULL COMMENT '证件信息',
  `sys_user_prov` varchar(40) DEFAULT NULL COMMENT 'Area的uid',
  `sys_prov_name` varchar(60) DEFAULT NULL COMMENT '省名称(冗余)',
  `sys_user_city` varchar(40) DEFAULT NULL COMMENT '市',
  `sys_city_name` varchar(60) DEFAULT NULL COMMENT '市名称(冗余)',
  `sys_user_img` varchar(128) DEFAULT NULL COMMENT '用户头像地址前缀，图片加1.jpg,2.jpg',
  `sys_img_count` int(11) DEFAULT NULL COMMENT '头像张数',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  `del_status` bit(1) DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_username` (`sys_username`),
  KEY `sys_user_uid` (`sys_user_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('33', '14919867773023781', '000000', '000000', 'admin', '', '', '', '', '', '', '', '', '', '0', '2017-04-12 16:46:17', '2017-05-04 11:16:46', '000000', '\0');
INSERT INTO `sys_user_info` VALUES ('44', '14938677976781479', '111111', '000000', '设备管理', '', '', '', '', '', '', '', '', '', null, '2017-05-04 11:16:37', '2017-05-04 11:16:37', '000000', '\0');
INSERT INTO `sys_user_info` VALUES ('45', '14938678304131101', '222222', '000000', '用户', '', '', '', '', '', '', '', '', '', null, '2017-05-04 11:17:10', '2017-05-04 11:17:10', '000000', '\0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `sys_user_uid` varchar(40) DEFAULT NULL COMMENT '用户表uid',
  `sys_role_uid` varchar(40) DEFAULT NULL COMMENT '角色表uid',
  `createtime` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `updatetime` varchar(40) DEFAULT NULL COMMENT '更新时间',
  `updateuid` varchar(40) DEFAULT NULL COMMENT '更新用户uid',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_uid1` (`sys_role_uid`),
  KEY `fk_user_role_uid2` (`sys_user_uid`),
  CONSTRAINT `fk_user_role_uid1` FOREIGN KEY (`sys_role_uid`) REFERENCES `sys_role_info` (`sys_role_uid`),
  CONSTRAINT `fk_user_role_uid2` FOREIGN KEY (`sys_user_uid`) REFERENCES `sys_user_info` (`sys_user_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('165', '14919867773023781', '14882759478049742', '2017-05-02 15:03:13', '2017-05-02 15:03:13', null);
INSERT INTO `sys_user_role` VALUES ('175', '14938677976781479', '14938677363549164', '2017-05-04 11:17:21', '2017-05-04 11:17:21', null);
INSERT INTO `sys_user_role` VALUES ('176', '14938678304131101', '14938677514102932', '2017-05-04 11:17:25', '2017-05-04 11:17:25', null);
