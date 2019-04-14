/*
Navicat MySQL Data Transfer

Source Server         : zx
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ed_system

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-03-19 04:13:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attendance_record`
-- ----------------------------
DROP TABLE IF EXISTS `attendance_record`;
CREATE TABLE `attendance_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `opt_id` int(11) DEFAULT NULL COMMENT '操作管理员id',
  `stu_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '学生username',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `createDate` datetime DEFAULT NULL COMMENT '创建日期',
  `absence_time` varchar(255) DEFAULT NULL COMMENT '缺席时间',
  `office_id` int(11) DEFAULT NULL COMMENT '专业id',
  `office_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '专业名称',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名称',
  `clazz_id` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of attendance_record
-- ----------------------------

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '班级名称',
  `office_id` int(11) DEFAULT NULL COMMENT '专业id',
  `del_flag` int(11) unsigned zerofill DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('2', '软件一班', '1', '00000000001');
INSERT INTO `clazz` VALUES ('3', '软件一班', '40', '00000000001');
INSERT INTO `clazz` VALUES ('4', '软件一班', '40', '00000000000');
INSERT INTO `clazz` VALUES ('5', '软件二班', '40', '00000000000');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(50) DEFAULT NULL COMMENT '课程编号',
  `course_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名称',
  `tId` int(50) DEFAULT NULL COMMENT '任课教师id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` int(20) DEFAULT '0' COMMENT '删除标识',
  `term` int(11) DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('4', 'NO20190319001', 'java编程语言', '72', '2019-03-19 02:54:59', null, '世界上最好的编程语言', '0', null);

-- ----------------------------
-- Table structure for `leave_info`
-- ----------------------------
DROP TABLE IF EXISTS `leave_info`;
CREATE TABLE `leave_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL COMMENT '学生id',
  `opt_id` int(11) DEFAULT NULL COMMENT '辅导员id',
  `status` int(11) DEFAULT '0' COMMENT '假条状态',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `start_time` varchar(255) DEFAULT NULL COMMENT '请假开始时间',
  `end_time` varchar(255) DEFAULT NULL COMMENT '请假结束时间',
  `rejection` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '驳回原因',
  `create_date` datetime DEFAULT NULL COMMENT '发起时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `stu_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '学生姓名',
  `opt_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of leave_info
-- ----------------------------
INSERT INTO `leave_info` VALUES ('12', '67', '1', '0', '哥哥结婚', '2019-03-19 ', ' 2019-03-19', null, '2019-03-19 03:06:52', null, null, null);
INSERT INTO `leave_info` VALUES ('13', '67', '1', '0', '弟弟生日', '2019-03-19 ', ' 2019-03-19', null, '2019-03-19 03:23:15', null, '张一', null);
INSERT INTO `leave_info` VALUES ('14', '68', '1', '0', '姐姐结婚', '2019-03-01 ', ' 2019-03-16', null, '2019-03-19 03:26:59', null, '张二', null);
INSERT INTO `leave_info` VALUES ('15', '68', '1', '0', '44', '2019-03-19 ', ' 2019-03-19', null, '2019-03-19 03:27:36', null, '张二', null);
INSERT INTO `leave_info` VALUES ('16', '67', '1', '0', '参加考试', '2019-03-19 ', ' 2019-03-19', null, '2019-03-19 03:31:27', null, '张一', null);
INSERT INTO `leave_info` VALUES ('17', '67', '73', '1', '哥哥结婚', '2019-03-19 ', ' 2019-03-19', null, '2019-03-19 03:45:39', '2019-03-19 03:46:15', '张一', null);

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL COMMENT '分数',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `stu_id` int(11) DEFAULT NULL COMMENT '学生id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  `opt_id` int(11) DEFAULT NULL COMMENT '操作员id',
  `office_id` int(11) DEFAULT NULL COMMENT '专业id',
  `clazz_id` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('8', '98', '4', '67', '2019-03-19 03:57:06', null, '73', '40', '4');

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
INSERT INTO `sys_office` VALUES ('39', '1', '0,1,', '计算机系', '100000001', '46', '15259178998', '123456', null, '2019-02-26 11:38:24', '2019-02-26 11:16:09', '0');
INSERT INTO `sys_office` VALUES ('40', '39', '0,1,39,', '软件专业', '100000001001', '73', '5345', '534534', null, '2019-03-19 02:56:28', '2019-02-26 11:38:37', '0');
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
INSERT INTO `sys_role` VALUES ('11', '辅导员', null, '1', '1', '2018-07-16 18:06:08', '2019-03-19 03:24:47', null, '0');
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
INSERT INTO `sys_role_menu` VALUES ('11', '188');
INSERT INTO `sys_role_menu` VALUES ('11', '189');
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
  `clazz_id` int(11) DEFAULT NULL COMMENT '班级编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_stu_clazz
-- ----------------------------
INSERT INTO `sys_stu_clazz` VALUES ('5', '66');
INSERT INTO `sys_stu_clazz` VALUES ('4', '67');
INSERT INTO `sys_stu_clazz` VALUES ('5', '68');

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
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_username` (`username`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '30', 'admin', '715a6ef69c55036a6d829b397acae622459669c0', 'ffd82a7254c4f077', '', '8675', '2013-05-27 08:00:00', '2018-01-12 16:43:49', '最高管理员', '0', null);
INSERT INTO `sys_user` VALUES ('67', '40', 'yi_zheng', '5edba32ce4028a8a06ba36655a7cc4b388cea497', '4cbe82ce9204ce6b', 'yi_zhang@ziguangcn.com', '15259178998', '2019-03-19 02:11:46', '2019-03-19 02:12:26', '第一', '0', '张一');
INSERT INTO `sys_user` VALUES ('68', '40', 'er_zheng', '0db43e8ac0bc671022802d32de07f69d8c734f32', 'c354aab1ce3bf4bd', 'er_zhang@ziguangcn.com', '15259178998', '2019-03-19 02:16:38', '2019-03-19 02:19:56', '张二', '0', '张二');
INSERT INTO `sys_user` VALUES ('72', null, ' zhang3', '1c289937899675abec945e4ad4aa690699f080eb', '305c09df7b8872ae', null, null, '2019-03-19 02:44:27', '2019-03-19 02:44:27', null, '0', '张晨晨');
INSERT INTO `sys_user` VALUES ('73', null, 'zhang4', 'c44f777b88207f61c80c43f66e55a76143b41bf0', '8cc9e76dc654768d', null, null, '2019-03-19 02:44:58', '2019-03-19 02:44:58', null, '0', '郑斌成');

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
INSERT INTO `sys_user_role` VALUES ('67', '14');
INSERT INTO `sys_user_role` VALUES ('68', '14');
INSERT INTO `sys_user_role` VALUES ('72', '13');
INSERT INTO `sys_user_role` VALUES ('73', '11');

-- ----------------------------
-- Table structure for `time_table`
-- ----------------------------
DROP TABLE IF EXISTS `time_table`;
CREATE TABLE `time_table` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(10) DEFAULT NULL COMMENT '课程id',
  `classroom` varchar(124) CHARACTER SET utf8 DEFAULT NULL COMMENT '班级',
  `office_id` varchar(124) DEFAULT NULL COMMENT '专业id',
  `day` int(124) DEFAULT NULL COMMENT '周几',
  `del_flag` int(11) unsigned zerofill DEFAULT NULL COMMENT '删除标识',
  `sort` int(12) DEFAULT NULL COMMENT '第几节',
  `clazz_id` int(11) DEFAULT NULL COMMENT '班级id',
  `term` int(11) DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `agdkey` (`clazz_id`,`sort`,`term`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of time_table
-- ----------------------------
INSERT INTO `time_table` VALUES ('17', '4', '机房一', '40', '1', '00000000000', '1', '4', '1');
INSERT INTO `time_table` VALUES ('18', '4', '机房2', '40', '2', '00000000000', '2', '4', '1');
INSERT INTO `time_table` VALUES ('19', '4', '机房3', '40', '1', '00000000000', '5', '4', '1');
INSERT INTO `time_table` VALUES ('20', '4', '机房5', '40', '3', '00000000000', '4', '4', '1');
