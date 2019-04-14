/*
Navicat MySQL Data Transfer

Source Server         : zx
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ed_system

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-03-13 01:12:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attendance_record`
-- ----------------------------
DROP TABLE IF EXISTS `attendance_record`;
CREATE TABLE `attendance_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `opt_id` int(11) DEFAULT NULL,
  `stu_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `absence_time` varchar(255) DEFAULT NULL,
  `office_id` int(11) DEFAULT NULL,
  `office_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `clazz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of attendance_record
-- ----------------------------
INSERT INTO `attendance_record` VALUES ('10', '66', '1', 'liu_liu', '444444444444', '2019-03-07 15:06:22', '2019-03-07 15:06:13 ~ 2019-03-13 15:06:00', '40', '软件专业', '1', '31233213', '5');

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `office_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `del_flag` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '32323', '1', null, '00000000001');
INSERT INTO `clazz` VALUES ('2', '软件一班', '1', null, '00000000001');
INSERT INTO `clazz` VALUES ('3', '软件一班', '40', null, '00000000001');
INSERT INTO `clazz` VALUES ('4', '软件一班', '40', null, '00000000000');
INSERT INTO `clazz` VALUES ('5', '软件二班', '40', null, '00000000000');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(50) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `tId` int(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(20) DEFAULT '0',
  `term` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'NO20190218001', '31233213', '51', '2019-02-18 14:16:10', '2019-02-28 19:50:17', '32131232312', '0', null);
INSERT INTO `course` VALUES ('2', 'NO20190218002', '534', '63', '2019-02-26 18:59:20', '2019-02-28 17:41:42', '5345', '0', null);
INSERT INTO `course` VALUES ('3', 'NO20190218003', '4342342', '63', '2019-02-28 17:05:21', '2019-02-28 17:41:50', '4334233244', '0', null);

-- ----------------------------
-- Table structure for `leave_info`
-- ----------------------------
DROP TABLE IF EXISTS `leave_info`;
CREATE TABLE `leave_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `opt_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `rejection` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `stu_name` varchar(255) DEFAULT NULL,
  `opt_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of leave_info
-- ----------------------------
INSERT INTO `leave_info` VALUES ('1', '1', '1', '1', '1', '2019-02-21 14:26:17', '2019-02-21 14:26:20', '1', '2019-02-21 14:32:03', '2019-02-21 20:01:22', '435345', '435345');
INSERT INTO `leave_info` VALUES ('2', '1', '1', '1', 'erwer', '2019-02-21 ', ' 2019-02-21', null, '2019-02-21 17:34:08', '2019-02-21 20:01:05', 'admin', null);
INSERT INTO `leave_info` VALUES ('3', '1', '1', '2', 'erew', '2019-02-21 ', ' 2019-02-21', '231231', '2019-02-21 17:34:25', '2019-02-21 19:39:56', 'admin', null);
INSERT INTO `leave_info` VALUES ('4', '1', '1', '2', '432423', '2019-02-21 ', ' 2019-02-21', '32131', '2019-02-21 17:39:32', '2019-02-21 19:36:53', 'admin', null);
INSERT INTO `leave_info` VALUES ('5', '1', '1', '2', '32423', '3242-03-01 ', ' 3242-03-01', '4234', '2019-02-21 17:39:51', '2019-02-21 19:36:32', 'admin', null);
INSERT INTO `leave_info` VALUES ('6', '1', '1', '2', '423423', '2019-02-21 ', ' 2019-02-21', 'ewqeq', '2019-02-21 17:43:51', '2019-02-21 19:35:12', 'admin', null);
INSERT INTO `leave_info` VALUES ('7', '1', '1', '1', '3123', '0003-01-01 ', ' 0003-01-01', null, '2019-02-21 20:01:34', '2019-02-21 20:01:41', 'admin', null);
INSERT INTO `leave_info` VALUES ('8', '1', '1', '1', '42342', '0004-01-01 ', ' 0004-01-01', null, '2019-02-21 20:02:07', '2019-02-21 20:02:14', 'admin', null);
INSERT INTO `leave_info` VALUES ('9', '1', '1', '0', '423423', '2019-02-25 ', ' 2019-02-25', null, '2019-02-25 16:55:59', null, 'admin', null);
INSERT INTO `leave_info` VALUES ('10', '66', '1', '0', '222', '2019-02-28 ', ' 2019-02-28', null, '2019-02-28 16:22:07', null, 'liu_liu', null);
INSERT INTO `leave_info` VALUES ('11', '1', '1', '0', '44444444444', '2019-02-28 ', ' 2019-02-28', null, '2019-02-28 17:48:31', null, 'admin', null);

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `opt_id` int(11) DEFAULT NULL,
  `office_id` int(11) DEFAULT NULL,
  `clazz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('3', '123', '1', '66', '2019-02-27 21:16:48', null, '1', '40', null);
INSERT INTO `score` VALUES ('4', '32', '2', '66', '2019-02-27 21:18:18', null, '1', '40', null);
INSERT INTO `score` VALUES ('5', '21123', '1', '66', '2019-02-28 19:42:06', null, '1', '40', null);
INSERT INTO `score` VALUES ('6', '12', '2', '66', '2019-03-07 16:51:59', null, '1', '40', '5');
INSERT INTO `score` VALUES ('7', '12345', '1', '66', '2019-03-07 16:55:08', null, '1', '40', '5');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '功能菜单', '0', null, null, null, '1', null, '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '系统设置', '10000', '', null, 'cog', '1', '', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('91', '2', '0,1,2,', '字典管理', '1070', '/system/dict/', '', 'th-list', '1', '', '2017-12-27 18:44:52', '', '1');
INSERT INTO `sys_menu` VALUES ('93', '2', '0,1,2,', '角色管理', '1040', '/system/role', '', 'lock', '1', '', '2017-12-27 18:44:17', '', '0');
INSERT INTO `sys_menu` VALUES ('94', '2', '0,1,2,', '菜单管理', '1010', '/system/menu', '', 'list-alt', '1', '', '2017-12-27 18:41:53', '', '0');
INSERT INTO `sys_menu` VALUES ('95', '2', '0,1,2,', '用户管理', '1000', '/system/user', '', 'user', '1', '', '2017-12-27 18:47:29', '', '1');
INSERT INTO `sys_menu` VALUES ('96', '2', '0,1,2,', '专业管理', '1100', '/system/office', null, 'th-large', '1', '', '2017-12-27 18:46:13', '', '0');
INSERT INTO `sys_menu` VALUES ('97', '94', '0,1,2,94,', '修改', '10001', '', null, '', '0', 'sys:menu:edit', '2017-12-27 10:08:44', null, '0');
INSERT INTO `sys_menu` VALUES ('98', '94', '0,1,2,94,', '查看', '1030', null, null, null, '0', 'sys:menu:view', '2017-12-27 10:09:37', null, '0');
INSERT INTO `sys_menu` VALUES ('99', '93', '0,1,2,93,', '修改', '1050', null, null, null, '0', 'sys:role:edit', '2017-12-28 16:27:05', null, '0');
INSERT INTO `sys_menu` VALUES ('100', '93', '0,1,2,93,', '查看', '1060', null, null, null, '0', 'sys:role:view', '2017-12-28 16:28:36', null, '0');
INSERT INTO `sys_menu` VALUES ('103', '96', '0,1,2,96,', '修改', '500', null, null, null, '0', 'sys:office:edit', '2018-01-02 09:52:34', null, '0');
INSERT INTO `sys_menu` VALUES ('104', '96', '0,1,2,96,', '查看', '400', null, null, null, '0', 'sys:office:view', '2018-01-02 09:53:16', null, '0');
INSERT INTO `sys_menu` VALUES ('105', '95', '0,1,2,95,', '查看', '100', null, null, null, '0', 'sys:user:view', '2018-01-03 15:21:03', null, '1');
INSERT INTO `sys_menu` VALUES ('106', '95', '0,1,2,95,', '修改', '200', null, null, null, '0', 'sys:user:edit', '2018-01-03 15:21:44', null, '1');
INSERT INTO `sys_menu` VALUES ('174', '1', '0,1,', '工作台', '1', '', null, 'laptop', '1', '', '2018-07-09 17:06:05', null, '0');
INSERT INTO `sys_menu` VALUES ('175', '174', '0,1,174,', '我的模板', '5', '/template', null, 'file-alt', '1', '', '2018-07-09 17:07:39', null, '1');
INSERT INTO `sys_menu` VALUES ('176', '174', '0,1,174,', '订单列表', '1', '/order/list', null, '', '1', '', '2018-07-12 09:47:08', null, '1');
INSERT INTO `sys_menu` VALUES ('177', '176', '0,1,176,', 'ttt', '1', '/order/list', null, '', '1', '', '2018-07-12 09:49:53', null, '1');
INSERT INTO `sys_menu` VALUES ('178', '174', '0,1,174,', '打印面单', '2', '/order/printList?status=0', null, '', '1', '', '2018-07-12 10:26:30', null, '1');
INSERT INTO `sys_menu` VALUES ('179', '174', '0,1,174,', '制作管理', '3', '/order/makeList?status=2', null, '', '1', '', '2018-07-12 11:42:29', null, '1');
INSERT INTO `sys_menu` VALUES ('180', '174', '0,1,174,', '发货管理', '4', '/order/deliveryList?status=4', null, '', '1', '', '2018-07-12 11:52:07', null, '1');
INSERT INTO `sys_menu` VALUES ('181', '1', '0,1,', '用户管理', '2', '', null, 'asterisk', '1', '', '2019-02-15 10:43:20', null, '0');
INSERT INTO `sys_menu` VALUES ('182', '181', '0,1,181,', '学生管理', '2', '/system/user/14', null, '', '1', '', '2019-02-15 10:44:25', null, '0');
INSERT INTO `sys_menu` VALUES ('183', '181', '0,1,181,', '教师管理', '2', '/system/user/13', null, '', '1', '', '2019-02-15 11:12:09', null, '0');
INSERT INTO `sys_menu` VALUES ('184', '181', '0,1,181,', '辅导员管理', '3', '/system/user/11', null, '', '1', '', '2019-02-15 11:13:09', null, '0');
INSERT INTO `sys_menu` VALUES ('185', '2', '0,1,2,', '课程管理', '4', '/system/course', null, '', '1', '', '2019-02-15 17:41:02', null, '0');
INSERT INTO `sys_menu` VALUES ('186', '174', '0,1,174,', '查看课程表', '4', '/timeTable/List/1', null, '', '1', '', '2019-02-18 15:42:44', null, '0');
INSERT INTO `sys_menu` VALUES ('187', '174', '0,1,174,', '请假申请', '2', '/leave/list', null, '', '1', '', '2019-02-21 11:13:35', null, '0');
INSERT INTO `sys_menu` VALUES ('188', '174', '0,1,174,', '请假审批', '3', '/leave/couList', null, '', '1', '', '2019-02-21 18:09:31', null, '0');
INSERT INTO `sys_menu` VALUES ('189', '174', '0,1,174,', '考勤查看', '2', '/attendance/list', null, '', '1', '', '2019-02-25 13:57:55', null, '0');
INSERT INTO `sys_menu` VALUES ('190', '174', '0,1,174,', '成绩管理', '6', '/score/list', null, '', '1', '', '2019-02-26 19:45:11', null, '0');
INSERT INTO `sys_menu` VALUES ('191', '190', '0,1,174,190,', '添加', '434', '', null, '', '0', 'sys:score:add', '2019-02-27 14:45:03', null, '1');
INSERT INTO `sys_menu` VALUES ('192', '174', '0,1,174,', '查看成绩', '4', '/score/personScore/1', null, '', '1', '', '2019-02-27 20:22:50', null, '0');
INSERT INTO `sys_menu` VALUES ('193', '174', '0,1,174,', '课程表安排', '2', '/timeTable/list', null, '', '1', '', '2019-02-28 13:51:03', null, '0');
INSERT INTO `sys_menu` VALUES ('194', '2', '0,1,2,', '班级管理', '2', '/clazz/list', null, '', '1', '', '2019-03-06 20:05:46', null, '0');

-- ----------------------------
-- Table structure for `sys_office`
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `USEABLE` varchar(64) DEFAULT NULL COMMENT '是否启用',
  `update_date` datetime NOT NULL,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '工程学院', '100000', null, null, null, '1', '2013-05-27 08:00:00', '2013-05-27 08:00:00', '0');
INSERT INTO `sys_office` VALUES ('31', '1', '0,1,', '外贸', '100000004', '', '', '', null, '2018-01-12 16:54:18', '2018-01-12 16:54:18', '1');
INSERT INTO `sys_office` VALUES ('32', '31', '0,1,31,', '一组', '100000004001', '', '', '', null, '2018-01-12 16:54:34', '2018-01-12 16:54:34', '1');
INSERT INTO `sys_office` VALUES ('33', '31', '0,1,31,', '二组', '100000004002', '', '', '', null, '2018-01-12 16:54:45', '2018-01-12 16:54:45', '1');
INSERT INTO `sys_office` VALUES ('34', '31', '0,1,31,', '三组', '100000004003', '', '', '', null, '2018-01-12 16:54:54', '2018-01-12 16:54:54', '1');
INSERT INTO `sys_office` VALUES ('35', '1', '0,1,', '未知分组', '100000002', '', '', '', null, '2018-01-12 16:55:05', '2018-01-12 16:55:05', '1');
INSERT INTO `sys_office` VALUES ('36', '34', '0,1,31,34,', '234', '100000004003001', '123', '23', '234', null, '2018-04-12 17:38:20', '2018-04-12 17:38:20', '1');
INSERT INTO `sys_office` VALUES ('37', '1', '0,1,', '采购部', '100000003', '', '', '', null, '2018-06-20 09:55:38', '2018-06-20 09:55:38', '1');
INSERT INTO `sys_office` VALUES ('38', '1', '0,1,', '销售部', '100000004', '', '', '', null, '2018-06-20 10:04:06', '2018-06-20 10:04:06', '1');
INSERT INTO `sys_office` VALUES ('39', '1', '0,1,', '计算机系', '100000001', '46', '15259178998', '123456', null, '2019-02-26 11:38:24', '2019-02-26 11:16:09', '0');
INSERT INTO `sys_office` VALUES ('40', '39', '0,1,39,', '软件专业', '100000001001', '46', '5345', '534534', null, '2019-02-26 11:39:06', '2019-02-26 11:38:37', '0');
INSERT INTO `sys_office` VALUES ('41', '40', '0,1,39,40,', '软件一班', '100000001001001', '46', '', '', null, '2019-03-05 13:58:45', '2019-03-05 13:58:45', '1');
INSERT INTO `sys_office` VALUES ('42', '39', '0,1,39,', '计科专业', '100000001002', '46', '', '', null, '2019-03-07 11:16:38', '2019-03-07 11:16:38', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `useable` varchar(64) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'assignment', '1', '1', '2013-05-27 08:00:00', '2018-05-31 11:48:35', null, '0');
INSERT INTO `sys_role` VALUES ('11', '辅导员', null, '1', '1', '2018-07-16 18:06:08', '2019-02-27 15:06:10', null, '0');
INSERT INTO `sys_role` VALUES ('13', '教师', null, '1', '1', '2018-07-16 18:20:57', '2019-02-28 19:49:10', null, '0');
INSERT INTO `sys_role` VALUES ('14', '学生', null, '1', '1', '2018-07-20 20:00:40', '2019-02-28 16:21:05', null, '0');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '104');
INSERT INTO `sys_role_menu` VALUES ('1', '105');
INSERT INTO `sys_role_menu` VALUES ('1', '106');
INSERT INTO `sys_role_menu` VALUES ('1', '108');
INSERT INTO `sys_role_menu` VALUES ('1', '109');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '113');
INSERT INTO `sys_role_menu` VALUES ('1', '114');
INSERT INTO `sys_role_menu` VALUES ('1', '115');
INSERT INTO `sys_role_menu` VALUES ('1', '116');
INSERT INTO `sys_role_menu` VALUES ('1', '117');
INSERT INTO `sys_role_menu` VALUES ('1', '118');
INSERT INTO `sys_role_menu` VALUES ('1', '119');
INSERT INTO `sys_role_menu` VALUES ('1', '120');
INSERT INTO `sys_role_menu` VALUES ('1', '121');
INSERT INTO `sys_role_menu` VALUES ('1', '124');
INSERT INTO `sys_role_menu` VALUES ('1', '125');
INSERT INTO `sys_role_menu` VALUES ('1', '126');
INSERT INTO `sys_role_menu` VALUES ('1', '127');
INSERT INTO `sys_role_menu` VALUES ('1', '129');
INSERT INTO `sys_role_menu` VALUES ('1', '130');
INSERT INTO `sys_role_menu` VALUES ('1', '131');
INSERT INTO `sys_role_menu` VALUES ('1', '132');
INSERT INTO `sys_role_menu` VALUES ('1', '133');
INSERT INTO `sys_role_menu` VALUES ('1', '134');
INSERT INTO `sys_role_menu` VALUES ('1', '135');
INSERT INTO `sys_role_menu` VALUES ('1', '136');
INSERT INTO `sys_role_menu` VALUES ('1', '137');
INSERT INTO `sys_role_menu` VALUES ('1', '138');
INSERT INTO `sys_role_menu` VALUES ('1', '139');
INSERT INTO `sys_role_menu` VALUES ('1', '140');
INSERT INTO `sys_role_menu` VALUES ('1', '141');
INSERT INTO `sys_role_menu` VALUES ('1', '142');
INSERT INTO `sys_role_menu` VALUES ('1', '143');
INSERT INTO `sys_role_menu` VALUES ('1', '144');
INSERT INTO `sys_role_menu` VALUES ('1', '145');
INSERT INTO `sys_role_menu` VALUES ('1', '146');
INSERT INTO `sys_role_menu` VALUES ('1', '147');
INSERT INTO `sys_role_menu` VALUES ('1', '148');
INSERT INTO `sys_role_menu` VALUES ('1', '149');
INSERT INTO `sys_role_menu` VALUES ('1', '150');
INSERT INTO `sys_role_menu` VALUES ('1', '151');
INSERT INTO `sys_role_menu` VALUES ('1', '152');
INSERT INTO `sys_role_menu` VALUES ('1', '153');
INSERT INTO `sys_role_menu` VALUES ('1', '155');
INSERT INTO `sys_role_menu` VALUES ('1', '156');
INSERT INTO `sys_role_menu` VALUES ('1', '157');
INSERT INTO `sys_role_menu` VALUES ('1', '158');
INSERT INTO `sys_role_menu` VALUES ('1', '159');
INSERT INTO `sys_role_menu` VALUES ('1', '160');
INSERT INTO `sys_role_menu` VALUES ('1', '161');
INSERT INTO `sys_role_menu` VALUES ('1', '162');
INSERT INTO `sys_role_menu` VALUES ('1', '163');
INSERT INTO `sys_role_menu` VALUES ('1', '164');
INSERT INTO `sys_role_menu` VALUES ('1', '165');
INSERT INTO `sys_role_menu` VALUES ('1', '166');
INSERT INTO `sys_role_menu` VALUES ('1', '167');
INSERT INTO `sys_role_menu` VALUES ('1', '168');
INSERT INTO `sys_role_menu` VALUES ('1', '169');
INSERT INTO `sys_role_menu` VALUES ('1', '170');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '27');
INSERT INTO `sys_role_menu` VALUES ('1', '91');
INSERT INTO `sys_role_menu` VALUES ('1', '93');
INSERT INTO `sys_role_menu` VALUES ('1', '94');
INSERT INTO `sys_role_menu` VALUES ('1', '95');
INSERT INTO `sys_role_menu` VALUES ('1', '96');
INSERT INTO `sys_role_menu` VALUES ('1', '97');
INSERT INTO `sys_role_menu` VALUES ('1', '98');
INSERT INTO `sys_role_menu` VALUES ('1', '99');
INSERT INTO `sys_role_menu` VALUES ('10', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '174');
INSERT INTO `sys_role_menu` VALUES ('10', '175');
INSERT INTO `sys_role_menu` VALUES ('10', '176');
INSERT INTO `sys_role_menu` VALUES ('11', '1');
INSERT INTO `sys_role_menu` VALUES ('11', '174');
INSERT INTO `sys_role_menu` VALUES ('11', '179');
INSERT INTO `sys_role_menu` VALUES ('11', '190');
INSERT INTO `sys_role_menu` VALUES ('12', '1');
INSERT INTO `sys_role_menu` VALUES ('12', '174');
INSERT INTO `sys_role_menu` VALUES ('12', '178');
INSERT INTO `sys_role_menu` VALUES ('13', '1');
INSERT INTO `sys_role_menu` VALUES ('13', '174');
INSERT INTO `sys_role_menu` VALUES ('13', '186');
INSERT INTO `sys_role_menu` VALUES ('13', '189');
INSERT INTO `sys_role_menu` VALUES ('13', '190');
INSERT INTO `sys_role_menu` VALUES ('14', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '174');
INSERT INTO `sys_role_menu` VALUES ('14', '186');
INSERT INTO `sys_role_menu` VALUES ('14', '187');
INSERT INTO `sys_role_menu` VALUES ('14', '192');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '117');
INSERT INTO `sys_role_menu` VALUES ('2', '118');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '93');
INSERT INTO `sys_role_menu` VALUES ('2', '94');
INSERT INTO `sys_role_menu` VALUES ('2', '95');
INSERT INTO `sys_role_menu` VALUES ('2', '96');
INSERT INTO `sys_role_menu` VALUES ('2', '97');
INSERT INTO `sys_role_menu` VALUES ('2', '98');
INSERT INTO `sys_role_menu` VALUES ('2', '99');
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '100');
INSERT INTO `sys_role_menu` VALUES ('3', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '27');
INSERT INTO `sys_role_menu` VALUES ('3', '92');
INSERT INTO `sys_role_menu` VALUES ('3', '93');
INSERT INTO `sys_role_menu` VALUES ('3', '94');
INSERT INTO `sys_role_menu` VALUES ('3', '95');
INSERT INTO `sys_role_menu` VALUES ('3', '96');
INSERT INTO `sys_role_menu` VALUES ('3', '97');
INSERT INTO `sys_role_menu` VALUES ('3', '98');
INSERT INTO `sys_role_menu` VALUES ('3', '99');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '27');
INSERT INTO `sys_role_menu` VALUES ('4', '57');
INSERT INTO `sys_role_menu` VALUES ('4', '58');
INSERT INTO `sys_role_menu` VALUES ('4', '59');
INSERT INTO `sys_role_menu` VALUES ('5', '1');
INSERT INTO `sys_role_menu` VALUES ('5', '2');
INSERT INTO `sys_role_menu` VALUES ('5', '27');
INSERT INTO `sys_role_menu` VALUES ('5', '93');
INSERT INTO `sys_role_menu` VALUES ('5', '99');
INSERT INTO `sys_role_menu` VALUES ('6', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '108');
INSERT INTO `sys_role_menu` VALUES ('6', '112');
INSERT INTO `sys_role_menu` VALUES ('6', '119');
INSERT INTO `sys_role_menu` VALUES ('6', '120');
INSERT INTO `sys_role_menu` VALUES ('6', '124');
INSERT INTO `sys_role_menu` VALUES ('6', '132');
INSERT INTO `sys_role_menu` VALUES ('6', '133');
INSERT INTO `sys_role_menu` VALUES ('6', '134');
INSERT INTO `sys_role_menu` VALUES ('6', '136');
INSERT INTO `sys_role_menu` VALUES ('6', '137');
INSERT INTO `sys_role_menu` VALUES ('6', '138');
INSERT INTO `sys_role_menu` VALUES ('6', '139');
INSERT INTO `sys_role_menu` VALUES ('6', '140');
INSERT INTO `sys_role_menu` VALUES ('6', '141');
INSERT INTO `sys_role_menu` VALUES ('6', '142');
INSERT INTO `sys_role_menu` VALUES ('6', '143');
INSERT INTO `sys_role_menu` VALUES ('6', '144');
INSERT INTO `sys_role_menu` VALUES ('6', '145');
INSERT INTO `sys_role_menu` VALUES ('6', '146');
INSERT INTO `sys_role_menu` VALUES ('6', '147');
INSERT INTO `sys_role_menu` VALUES ('6', '150');
INSERT INTO `sys_role_menu` VALUES ('6', '152');
INSERT INTO `sys_role_menu` VALUES ('6', '153');
INSERT INTO `sys_role_menu` VALUES ('6', '155');
INSERT INTO `sys_role_menu` VALUES ('6', '156');
INSERT INTO `sys_role_menu` VALUES ('6', '164');
INSERT INTO `sys_role_menu` VALUES ('6', '165');
INSERT INTO `sys_role_menu` VALUES ('6', '27');
INSERT INTO `sys_role_menu` VALUES ('7', '1');
INSERT INTO `sys_role_menu` VALUES ('7', '105');
INSERT INTO `sys_role_menu` VALUES ('7', '106');
INSERT INTO `sys_role_menu` VALUES ('7', '2');
INSERT INTO `sys_role_menu` VALUES ('7', '95');
INSERT INTO `sys_role_menu` VALUES ('8', '1');
INSERT INTO `sys_role_menu` VALUES ('8', '108');
INSERT INTO `sys_role_menu` VALUES ('8', '109');
INSERT INTO `sys_role_menu` VALUES ('8', '112');
INSERT INTO `sys_role_menu` VALUES ('8', '117');
INSERT INTO `sys_role_menu` VALUES ('8', '118');
INSERT INTO `sys_role_menu` VALUES ('8', '119');
INSERT INTO `sys_role_menu` VALUES ('8', '120');
INSERT INTO `sys_role_menu` VALUES ('8', '121');
INSERT INTO `sys_role_menu` VALUES ('8', '124');
INSERT INTO `sys_role_menu` VALUES ('8', '125');
INSERT INTO `sys_role_menu` VALUES ('8', '126');
INSERT INTO `sys_role_menu` VALUES ('8', '127');
INSERT INTO `sys_role_menu` VALUES ('8', '129');
INSERT INTO `sys_role_menu` VALUES ('8', '130');
INSERT INTO `sys_role_menu` VALUES ('8', '131');
INSERT INTO `sys_role_menu` VALUES ('8', '132');
INSERT INTO `sys_role_menu` VALUES ('8', '133');
INSERT INTO `sys_role_menu` VALUES ('8', '134');
INSERT INTO `sys_role_menu` VALUES ('8', '135');
INSERT INTO `sys_role_menu` VALUES ('8', '136');
INSERT INTO `sys_role_menu` VALUES ('8', '137');
INSERT INTO `sys_role_menu` VALUES ('8', '138');
INSERT INTO `sys_role_menu` VALUES ('8', '139');
INSERT INTO `sys_role_menu` VALUES ('8', '140');
INSERT INTO `sys_role_menu` VALUES ('8', '141');
INSERT INTO `sys_role_menu` VALUES ('8', '142');
INSERT INTO `sys_role_menu` VALUES ('8', '143');
INSERT INTO `sys_role_menu` VALUES ('8', '144');
INSERT INTO `sys_role_menu` VALUES ('8', '145');
INSERT INTO `sys_role_menu` VALUES ('8', '146');
INSERT INTO `sys_role_menu` VALUES ('8', '147');
INSERT INTO `sys_role_menu` VALUES ('8', '150');
INSERT INTO `sys_role_menu` VALUES ('8', '151');
INSERT INTO `sys_role_menu` VALUES ('8', '152');
INSERT INTO `sys_role_menu` VALUES ('8', '153');
INSERT INTO `sys_role_menu` VALUES ('8', '155');
INSERT INTO `sys_role_menu` VALUES ('8', '156');
INSERT INTO `sys_role_menu` VALUES ('8', '164');
INSERT INTO `sys_role_menu` VALUES ('8', '165');
INSERT INTO `sys_role_menu` VALUES ('8', '27');
INSERT INTO `sys_role_menu` VALUES ('9', '1');
INSERT INTO `sys_role_menu` VALUES ('9', '108');
INSERT INTO `sys_role_menu` VALUES ('9', '109');
INSERT INTO `sys_role_menu` VALUES ('9', '110');
INSERT INTO `sys_role_menu` VALUES ('9', '111');
INSERT INTO `sys_role_menu` VALUES ('9', '117');
INSERT INTO `sys_role_menu` VALUES ('9', '118');
INSERT INTO `sys_role_menu` VALUES ('9', '119');
INSERT INTO `sys_role_menu` VALUES ('9', '120');
INSERT INTO `sys_role_menu` VALUES ('9', '121');
INSERT INTO `sys_role_menu` VALUES ('9', '125');
INSERT INTO `sys_role_menu` VALUES ('9', '126');
INSERT INTO `sys_role_menu` VALUES ('9', '127');
INSERT INTO `sys_role_menu` VALUES ('9', '129');
INSERT INTO `sys_role_menu` VALUES ('9', '130');
INSERT INTO `sys_role_menu` VALUES ('9', '131');
INSERT INTO `sys_role_menu` VALUES ('9', '132');
INSERT INTO `sys_role_menu` VALUES ('9', '133');
INSERT INTO `sys_role_menu` VALUES ('9', '134');
INSERT INTO `sys_role_menu` VALUES ('9', '135');
INSERT INTO `sys_role_menu` VALUES ('9', '136');
INSERT INTO `sys_role_menu` VALUES ('9', '137');
INSERT INTO `sys_role_menu` VALUES ('9', '138');
INSERT INTO `sys_role_menu` VALUES ('9', '139');
INSERT INTO `sys_role_menu` VALUES ('9', '140');
INSERT INTO `sys_role_menu` VALUES ('9', '141');
INSERT INTO `sys_role_menu` VALUES ('9', '142');
INSERT INTO `sys_role_menu` VALUES ('9', '143');
INSERT INTO `sys_role_menu` VALUES ('9', '144');
INSERT INTO `sys_role_menu` VALUES ('9', '145');
INSERT INTO `sys_role_menu` VALUES ('9', '146');
INSERT INTO `sys_role_menu` VALUES ('9', '147');
INSERT INTO `sys_role_menu` VALUES ('9', '150');
INSERT INTO `sys_role_menu` VALUES ('9', '151');
INSERT INTO `sys_role_menu` VALUES ('9', '152');
INSERT INTO `sys_role_menu` VALUES ('9', '153');
INSERT INTO `sys_role_menu` VALUES ('9', '155');
INSERT INTO `sys_role_menu` VALUES ('9', '156');
INSERT INTO `sys_role_menu` VALUES ('9', '164');

-- ----------------------------
-- Table structure for `sys_stu_clazz`
-- ----------------------------
DROP TABLE IF EXISTS `sys_stu_clazz`;
CREATE TABLE `sys_stu_clazz` (
  `clazz_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_stu_clazz
-- ----------------------------
INSERT INTO `sys_stu_clazz` VALUES ('5', '66');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属部门',
  `username` varchar(100) NOT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(16) DEFAULT NULL COMMENT '密码盐',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `os_username` varchar(255) DEFAULT NULL,
  `os_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_username` (`username`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '30', 'admin', '715a6ef69c55036a6d829b397acae622459669c0', 'ffd82a7254c4f077', null, '', '8675', null, '2013-05-27 08:00:00', '2018-01-12 16:43:49', '最高管理员', '0', null, null);
INSERT INTO `sys_user` VALUES ('2', '37', 'admin5', '1deeb5f13fc78473aa67ffad8b6be59502f7c269', 'f61f80700de485eb', null, '378928908@qq.com', '234234', null, '2018-10-19 11:06:50', '2019-02-15 17:30:18', '3231', '1', null, null);
INSERT INTO `sys_user` VALUES ('33', '32', 'xxx', '8b134da2f712a939b114ea8769f3b3063f443d66', null, null, '', '', null, '2018-06-12 17:12:22', '2018-06-12 17:12:43', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('34', null, 'admin2', 'af37e0331baf536b21e9a8b5d62c545f7981cd55', null, null, null, null, null, '2018-06-12 17:14:32', '2018-06-12 17:14:32', null, '1', null, null);
INSERT INTO `sys_user` VALUES ('35', null, 'admin3', 'f0ba63465f1267070cf3aac340f9942af380a5b0', null, null, null, null, null, '2018-06-12 17:18:32', '2018-06-12 17:18:32', null, '1', null, null);
INSERT INTO `sys_user` VALUES ('36', null, 'admin4', '40221cc64d0ca841e5448087495c2415d5643740', '758fb2cda7c20748', null, null, null, null, '2018-06-12 17:19:53', '2018-06-12 17:19:53', null, '1', null, null);
INSERT INTO `sys_user` VALUES ('37', null, 'yang', 'dcc377bc547e00b69fa07296f2a6939d49bcb27d', '5aede3c5174032e1', null, null, null, null, '2018-06-20 09:44:23', '2018-06-20 09:44:23', null, '1', null, null);
INSERT INTO `sys_user` VALUES ('38', '37', '东哥', 'c4e7970e77b2e7c7abc46749b81f398ae3a93747', 'c10e3b90d7ff46a1', null, '', '', null, '2018-06-20 09:44:43', '2018-06-20 09:56:03', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('40', '37', 'DG', '5b43a3ac8bea08a876b33395d69ea910769f3c5f', '86c80568665da5ad', null, '', '', null, '2018-06-20 09:58:22', '2018-07-05 10:06:42', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('43', '38', 'dongge', '3ec6a6bd72ce8efa1e84f312f37f4c319fb8b15f', '7dd8737c5c019233', null, '', '', null, '2018-06-20 09:59:15', '2018-06-20 10:05:28', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('44', '35', '订单管理', 'b94ee6789ebef59ac6a897559ea0426671773162', '5a0e583611636bf9', null, '', '', null, '2018-07-16 18:15:32', '2018-07-16 18:19:19', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('45', '38', '面单管理', '5695d6b1f71b2ef584691047f03cdc3e2b56dbd8', 'f41447c551a2e095', null, '', '', null, '2018-07-16 18:18:46', '2018-07-16 18:19:38', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('46', '37', '美工', '525b8d3b9b018a864b35a01ff6ccdaea0eaf3d2b', '978f3bf0e1d12fa5', null, '', '', null, '2018-07-16 18:20:08', '2018-07-16 18:20:21', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('47', '38', '发货', 'ba1d1b01d540c578d6a2a410ebc76284c067ef1d', '324d45f4e8af2eb6', null, '', '', null, '2018-07-16 18:20:40', '2018-07-16 18:21:10', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('48', '37', '美工2', 'c1a6e7b257b977f0143a3e0eb2c4d2a9b3800a51', 'e5e1f5e74d6cbb77', null, '', '', null, '2018-07-20 19:03:16', '2018-07-20 19:04:16', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('49', '40', '发货2', 'bf081a3321523352d24503c328ee345513c34088', '4003199f518f1fda', null, '', '', null, '2018-07-20 20:00:23', '2018-07-20 20:01:51', '', '1', null, null);
INSERT INTO `sys_user` VALUES ('51', '38', 'xiang_zheng', '4b08ec1c4fa4dc4b750890f4b488bbc84c6e8ff5', 'dd35a8dfb77a2213', null, '', '', null, '2019-02-15 15:32:56', '2019-02-15 16:14:11', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('52', null, '4234', '6104931d4ce5c023859ae05cbea5238e3270c289', '63f44d2a2656e7c4', null, null, null, null, '2019-02-15 15:35:26', '2019-02-15 15:35:26', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('53', null, 'admin543534534', 'e8d57a17c37e761ca51643fb2f82be403cd33669', '1a7ef785fa2432ac', null, null, null, null, '2019-02-15 15:48:24', '2019-02-15 15:48:24', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('54', null, 'admin5435345645645', '769c2bb16829779ed213b79409a3ed9ea44f4e76', '09a61d9d47d8b51a', null, null, null, null, '2019-02-15 16:51:11', '2019-02-15 16:51:11', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('55', null, '4234234', 'a28d3659c4937f0e7c6a7bff65c483dd75c57a66', '9d11927495e5afcf', null, null, null, null, '2019-02-15 16:55:47', '2019-02-15 16:55:47', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('56', null, '21312312343242', '3f1884a7f6c0428e3f60db7f05ada990e115b508', '859aeea71dbfadc6', null, null, null, null, '2019-02-15 16:56:48', '2019-02-15 16:56:48', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('57', null, 'efgd', 'd6f80cd15fbded12e927092132be652740bd4cef', '80f79e00b5257ba0', null, null, null, null, '2019-02-15 16:59:10', '2019-02-15 16:59:10', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('58', '37', 'fsffgsdfsd', 'b9df91f0972b95eda4070ec88aef23a5caad4b27', 'fd3c830c037ababe', null, '', '', null, '2019-02-15 17:00:11', '2019-02-15 17:26:30', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('59', '37', 'dsdds', '31a467e618f380f6ec3229473c164ab66e647c70', 'ae2873ff4306d4ac', null, '', '', null, '2019-02-15 17:27:24', '2019-02-15 17:27:32', '', '0', null, null);
INSERT INTO `sys_user` VALUES ('60', '40', 'rwerwer', '96f827f1b6b2520cde8a56f79783b66bbe4601bb', 'ee6d39b8b443a9f7', null, '378928908@qq.com', '123456', null, '2019-02-15 17:28:44', '2019-02-27 20:08:48', '23434', '1', null, null);
INSERT INTO `sys_user` VALUES ('63', null, '3123', 'cf119d144ed28d83c16626766d5bf9c446c7e421', '544ac1fe1ec073c9', null, '378928908@qq.com', '34324', null, '2019-02-27 14:59:36', '2019-02-27 20:09:04', '23423', '0', null, null);
INSERT INTO `sys_user` VALUES ('64', null, 'xiang_zheng3', '4bf4f21c76f5e9653efab1a9f3662a23c8d546be', 'e164cf2878fb3e84', null, null, null, null, '2019-02-27 15:03:17', '2019-02-27 15:03:17', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('65', null, 'xiang_zheng2', 'b7d93f9bf776276f964d84ca24f13b1d4fc0d6c7', 'b524aab326648c3d', null, null, null, null, '2019-02-27 15:05:19', '2019-02-27 15:05:19', null, '0', null, null);
INSERT INTO `sys_user` VALUES ('66', '40', 'liu_liu', '51f56bd8f1be8c81c2d6101b86c6e04ea3bc6301', '3a1e8fdab094aa42', null, '', '', null, '2019-02-27 21:16:02', '2019-03-07 11:44:31', '', '0', null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('10', '2');
INSERT INTO `sys_user_role` VALUES ('11', '3');
INSERT INTO `sys_user_role` VALUES ('12', '4');
INSERT INTO `sys_user_role` VALUES ('13', '5');
INSERT INTO `sys_user_role` VALUES ('14', '6');
INSERT INTO `sys_user_role` VALUES ('16', '6');
INSERT INTO `sys_user_role` VALUES ('18', '6');
INSERT INTO `sys_user_role` VALUES ('2', '14');
INSERT INTO `sys_user_role` VALUES ('20', '6');
INSERT INTO `sys_user_role` VALUES ('21', '6');
INSERT INTO `sys_user_role` VALUES ('22', '6');
INSERT INTO `sys_user_role` VALUES ('23', '6');
INSERT INTO `sys_user_role` VALUES ('24', '6');
INSERT INTO `sys_user_role` VALUES ('27', '1');
INSERT INTO `sys_user_role` VALUES ('3', '6');
INSERT INTO `sys_user_role` VALUES ('30', '1');
INSERT INTO `sys_user_role` VALUES ('33', '5');
INSERT INTO `sys_user_role` VALUES ('38', '6');
INSERT INTO `sys_user_role` VALUES ('4', '3');
INSERT INTO `sys_user_role` VALUES ('40', '5');
INSERT INTO `sys_user_role` VALUES ('40', '6');
INSERT INTO `sys_user_role` VALUES ('40', '9');
INSERT INTO `sys_user_role` VALUES ('43', '5');
INSERT INTO `sys_user_role` VALUES ('43', '6');
INSERT INTO `sys_user_role` VALUES ('44', '10');
INSERT INTO `sys_user_role` VALUES ('45', '12');
INSERT INTO `sys_user_role` VALUES ('46', '11');
INSERT INTO `sys_user_role` VALUES ('47', '13');
INSERT INTO `sys_user_role` VALUES ('48', '11');
INSERT INTO `sys_user_role` VALUES ('49', '14');
INSERT INTO `sys_user_role` VALUES ('5', '4');
INSERT INTO `sys_user_role` VALUES ('51', '13');
INSERT INTO `sys_user_role` VALUES ('6', '5');
INSERT INTO `sys_user_role` VALUES ('60', '14');
INSERT INTO `sys_user_role` VALUES ('63', '13');
INSERT INTO `sys_user_role` VALUES ('64', '13');
INSERT INTO `sys_user_role` VALUES ('65', '11');
INSERT INTO `sys_user_role` VALUES ('66', '14');
INSERT INTO `sys_user_role` VALUES ('7', '2');
INSERT INTO `sys_user_role` VALUES ('7', '7');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '1');

-- ----------------------------
-- Table structure for `time_table`
-- ----------------------------
DROP TABLE IF EXISTS `time_table`;
CREATE TABLE `time_table` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(10) DEFAULT NULL,
  `classroom` varchar(124) CHARACTER SET utf8 DEFAULT NULL,
  `office_id` varchar(124) DEFAULT NULL,
  `day` int(124) DEFAULT NULL,
  `del_flag` int(11) unsigned zerofill DEFAULT NULL,
  `sort` int(12) DEFAULT NULL,
  `clazz_id` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `agdkey` (`clazz_id`,`sort`,`term`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of time_table
-- ----------------------------
INSERT INTO `time_table` VALUES ('1', '1', '165565', '1', '1', '00000000001', '2', null, null);
INSERT INTO `time_table` VALUES ('2', '2', '434', '39', '1', '00000000001', '1', null, null);
INSERT INTO `time_table` VALUES ('3', '1', '43423', '1', '1', '00000000001', '1', null, null);
INSERT INTO `time_table` VALUES ('4', '1', 'eweqw', '2', '4', '00000000001', '5', null, null);
INSERT INTO `time_table` VALUES ('5', '2', '323', '40', '3', '00000000001', '3', null, null);
INSERT INTO `time_table` VALUES ('6', '1', '43423', '2', '1', '00000000001', '1', null, null);
INSERT INTO `time_table` VALUES ('7', '1', '434', '40', '1', '00000000000', '4', '5', null);
INSERT INTO `time_table` VALUES ('8', '1', '32343', '40', '2', '00000000000', '2', null, null);
INSERT INTO `time_table` VALUES ('9', '1', '14343', '1', '1', '00000000001', '5', null, null);
INSERT INTO `time_table` VALUES ('10', '1', '543534', '40', '2', '00000000000', '2', null, null);
INSERT INTO `time_table` VALUES ('11', '3', '545345', '40', '1', '00000000000', '3', '5', '1');
INSERT INTO `time_table` VALUES ('12', '1', '多媒体', '40', '1', '00000000001', '1', null, null);
INSERT INTO `time_table` VALUES ('13', '1', '风格风格', '40', '1', '00000000001', '1', null, null);
INSERT INTO `time_table` VALUES ('14', '1', '4343', '40', '1', '00000000001', '1', '4', null);
INSERT INTO `time_table` VALUES ('15', '1', '4343423', '40', '1', '00000000000', '1', '5', '1');
INSERT INTO `time_table` VALUES ('16', '1', '4343', '40', '1', '00000000000', '2', '5', '1');
