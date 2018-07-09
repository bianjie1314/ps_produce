/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.1.49-community : Database - sop
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sop`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE sys_dict
(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
	value varchar(100) NOT NULL COMMENT '数据值',
	label varchar(100) NOT NULL COMMENT '标签名',
	type varchar(100) NOT NULL COMMENT '类型',
	description varchar(100) NOT NULL COMMENT '描述',
	sort decimal(10,0) NOT NULL COMMENT '排序（升序）',
	parent_id varchar(64) DEFAULT '0' COMMENT '父级编号',
	create_by varchar(64)  NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64)  NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_menu` */

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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`parent_ids`,`name`,`sort`,`href`,`target`,`icon`,`is_show`,`permission`,`create_by`,`create_date`,`remarks`,`del_flag`) values (1,'0','0,','功能菜单','0',NULL,NULL,NULL,'1',NULL,'1','2013-05-27 08:00:00',NULL,'0'),(2,'1','0,1,','系统设置','900',NULL,NULL,NULL,'1',NULL,'1','2013-05-27 08:00:00',NULL,'0'),(27,'1','0,1,','我的面板','100',NULL,NULL,NULL,'1',NULL,'1','2013-05-27 08:00:00',NULL,'0'),(90,'27','0,1,27,','修改密码','90','/system/user/modifyPwd','','lock','1','','1','2017-12-27 18:48:46','','0'),(91,'2','0,1,2,','字典管理','1070','/system/dict/','','th-list','1','','1','2017-12-27 18:44:52','','0'),(92,'27','0,1,27,','个人信息','60','/system/user/info',NULL,'user','1','','1','2017-12-27 18:48:19','','0'),(93,'2','0,1,2,','角色管理','1040','/system/role','','lock','1','','1','2017-12-27 18:44:17','','0'),(94,'2','0,1,2,','菜单管理','1010','/system/menu','','list-alt','1','','1','2017-12-27 18:41:53','','0'),(95,'2','0,1,2,','用户管理','1000','/system/user','','user','1','','1','2017-12-27 18:47:29','','0'),(96,'2','0,1,2,','部门管理','1100','/system/office','','th-large','1','','1','2017-12-27 18:46:13','','0'),(97,'94','0,1,2,94,','修改1','10001','',NULL,'','0','sys:menu:edit','1','2017-12-27 10:08:44',NULL,'0'),(98,'94','0,1,2,94,','查看','1030',NULL,NULL,NULL,'0','sys:menu:view','1','2017-12-27 10:09:37',NULL,'0'),(99,'93','0,1,2,93,','修改','1050',NULL,NULL,NULL,'0','sys:role:edit','1','2017-12-28 16:27:05',NULL,'0'),(100,'93','0,1,2,93,','查看','1060',NULL,NULL,NULL,'0','sys:role:view','1','2017-12-28 16:28:36',NULL,'0'),(103,'96','0,1,2,96,','修改','500',NULL,NULL,NULL,'0','sys:office:edit','1','2018-01-02 09:52:34',NULL,'0'),(104,'96','0,1,2,96,','查看','400',NULL,NULL,NULL,'0','sys:office:view','1','2018-01-02 09:53:16',NULL,'0'),(105,'95','0,1,2,95,','查看','100',NULL,NULL,NULL,'0','sys:user:view','1','2018-01-03 15:21:03',NULL,'0'),(106,'95','0,1,2,95,','修改','200',NULL,NULL,NULL,'0','sys:user:edit','1','2018-01-03 15:21:44',NULL,'0'),(107,'1','0,1,','t','122','',NULL,'','1','','1','2018-01-04 15:25:07',NULL,'1');

/*Table structure for table `sys_office` */

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='机构表';

/*Data for the table `sys_office` */

insert  into `sys_office`(`id`,`parent_id`,`parent_ids`,`name`,`code`,`master`,`phone`,`email`,`USEABLE`,`update_date`,`create_date`,`del_flag`) values (1,'0','0,','山东省总公司','100000',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(2,'1','0,1,','公司领导','100001',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(3,'1','0,1,','综合部','100002','','','','1','2018-01-02 20:13:52','2013-05-27 08:00:00','0'),(4,'1','0,1,','市场部1','1000031','11','1','129d908@sina.com','1','2018-01-03 09:12:38','2013-05-27 08:00:00','1'),(5,'1','0,1,','技术部','100004',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(6,'1','0,1,','研发部','100005',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(7,'1','0,1,','济南市分公司','200000',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(8,'7','0,1,7,','公司领导','200001',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(9,'7','0,1,7,','综合部','200002',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(10,'7','0,1,7,','市场部','200003',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(11,'7','0,1,7,','技术部','200004',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(12,'7','0,1,7,','历城区分公司','201000',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(13,'12','0,1,7,12,','公司领导','201001',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(14,'12','0,1,7,12,','综合部','201002',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(15,'12','0,1,7,12,','市场部','201003',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(16,'12','0,1,7,12,','技术部','201004',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(17,'7','0,1,7,','历下区分公司','201010',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(18,'17','0,1,7,17,','公司领导','201011',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(19,'17','0,1,7,17,','综合部','201012',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(20,'17','0,1,7,17,','市场部','201013',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(21,'17','0,1,7,17,','技术部','201014',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','0'),(22,'7','0,1,7,','高新区分公司','201010',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(23,'22','0,1,7,22,','公司领导','201011',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(24,'22','0,1,7,22,','综合部','201012',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(25,'22','0,1,7,22,','市场部','201013',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(26,'22','0,1,7,22,','技术部','201014',NULL,NULL,NULL,'1','2013-05-27 08:00:00','2013-05-27 08:00:00','1'),(27,'2','0,1,2,','kk','100001001','2','3','4',NULL,'2018-01-03 09:20:18','2018-01-03 09:20:18','0');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`role_type`,`data_scope`,`useable`,`create_date`,`update_date`,`remarks`,`del_flag`) values (1,'系统管理员','assignment','1','1','2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(2,'公司管理员','assignment','2','1','2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(3,'本公司管理员1','assignment','3','1','2013-05-27 08:00:00','2018-01-03 13:46:03',NULL,'0'),(4,'部门管理员','assignment','4','1','2013-05-27 08:00:00','2017-12-26 18:14:57','','0'),(5,'本部门管理员','assignment','5','1','2013-05-27 08:00:00','2017-12-26 15:43:28','','0'),(6,'普通用户','assignment','8','1','2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(7,'济南市管理员','assignment','3','1','2013-05-27 08:00:00','2018-01-04 10:22:28',NULL,'0');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values ('1','1'),('1','100'),('1','2'),('1','24'),('1','27'),('1','57'),('1','58'),('1','59'),('1','85'),('1','90'),('1','91'),('1','92'),('1','93'),('1','94'),('1','95'),('1','96'),('1','99'),('2','1'),('2','100'),('2','2'),('2','85'),('2','93'),('2','94'),('2','95'),('2','96'),('2','97'),('2','98'),('2','99'),('3','1'),('3','100'),('3','2'),('3','27'),('3','92'),('3','93'),('3','94'),('3','95'),('3','96'),('3','97'),('3','98'),('3','99'),('4','1'),('4','27'),('4','57'),('4','58'),('4','59'),('5','1'),('5','2'),('5','24'),('5','27'),('5','57'),('5','58'),('5','59'),('7','1'),('7','105'),('7','106'),('7','2'),('7','95');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属部门',
  `username` varchar(100) NOT NULL COMMENT '姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(16) NOT NULL COMMENT '密码盐',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_username` (`username`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`office_id`,`username`,`password`,`salt`,`no`,`email`,`phone`,`user_type`,`create_date`,`update_date`,`remarks`,`del_flag`) values (1,'2','admin','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0001','thinkgem@163.com','8675',NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00','最高管理员','0'),(2,'2','admin12','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','00022','Vemigg11gret@outlook.com','123',NULL,'2013-05-27 08:00:00','2018-01-04 13:52:55','123','0'),(3,'3','admin2','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0003',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(4,'4','a','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0004',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(5,'5','b','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0005',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(6,'6','c','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0006',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(7,'8','d','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0007',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(8,'9','e','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0008',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(9,'10','f','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0009',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(10,'11','g','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0010',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'0'),(11,'13','h','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0011',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'1'),(12,'18','i','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0012',NULL,NULL,NULL,'2013-05-27 08:00:00','2013-05-27 08:00:00',NULL,'1'),(13,'23','j','2b16690a2976b84ae1c5a7156f4531ba4b35c0aa','ec7799e5e0ae078e','0013','','','2','2013-05-27 08:00:00','2017-12-26 15:42:38','','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values ('1','1'),('1','2'),('10','2'),('11','3'),('12','4'),('13','5'),('14','6'),('2','3'),('2','6'),('2','7'),('3','2'),('4','3'),('5','4'),('6','5'),('7','2'),('7','7'),('8','2'),('9','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

DROP TABLE IF EXISTS `sys_flow`;
CREATE TABLE `sys_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '流程名称',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `updae_date` datetime NOT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS `sys_flow_line`;
CREATE TABLE `sys_flow_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_id` bigint(20) NOT NULL COMMENT '流程id',
  `from` bigint(20) DEFAULT NULL COMMENT '前一审批人',
  `current` bigint(20) NOT NULL COMMENT '当前审批人',
  `to` bigint(20) DEFAULT NULL COMMENT '下一审批人',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS `sys_approval_comments`;
CREATE TABLE `sys_approve_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_id` bigint(20) NOT NULL COMMENT '流程编号',
  `approve_id` bigint(20) NOT NULL COMMENT '审批事件编号',
  `user_id` bigint(20) NOT NULL COMMENT '审批人编号',
  `username` varchar(255) NOT NULL COMMENT '审批人名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审批状态 0:通过 1:驳回',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS `sys_approve`;
CREATE TABLE `sys_approve` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `product_no` varchar(50) NOT NULL COMMENT '产品编号',
  `product_sku` varchar(100) NOT NULL COMMENT '产品sku',
  `product_title` varchar(500) DEFAULT NULL COMMENT '产品标题',
  `product_image` varchar(500) DEFAULT NULL COMMENT '产品图片',
  `number` int(11) NOT NULL DEFAULT '0' COMMENT '采购数量',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '审批状态',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '审批类型',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8





/**  ----------------备货计划新增sql------------------------**/
/**
 * 智能备货   库存不足提醒
 */
ALTER TABLE `mkt_process` ADD COLUMN `produce_cycle` INT NULL COMMENT '生产周期' AFTER `head_range_sea_cost`; 
ALTER TABLE `sku_inventory` ADD COLUMN `process_id` BIGINT NULL COMMENT '精品id' AFTER `username`; 
ALTER TABLE `sku_inventory` ADD COLUMN `express_type` VARCHAR(50) NULL COMMENT '物流方式' AFTER `username`;
ALTER TABLE `sku_inventory` ADD COLUMN `reference_sales` DOUBLE(5,1) NULL COMMENT '参考销量' AFTER `express_type`;
ALTER TABLE `sku_inventory` ADD COLUMN `add_warehouse_time` INT NULL COMMENT '入库时间' AFTER `reference_sales`;
ALTER TABLE `sku_inventory` ADD COLUMN `pack_time` INT NULL COMMENT '包装时间' AFTER `add_warehouse_time`;
ALTER TABLE `sku_inventory` ADD UNIQUE INDEX `sku_sellerid_unique` (`sku`, `seller_id`); 
ALTER TABLE `sku_inventory_history` ADD COLUMN `process_id` BIGINT NULL AFTER `username`; 
/**
 * 库存同步历史记录
 */

DROP TABLE IF EXISTS `sku_inventory_history`;
CREATE TABLE `sku_inventory_history` (
  `sku` VARCHAR(255) NOT NULL,
  `fnsku` VARCHAR(255) DEFAULT NULL,
  `asin` VARCHAR(255) DEFAULT NULL,
  `product_name` VARCHAR(255) DEFAULT NULL,
  `condition` VARCHAR(255) DEFAULT NULL,
  `your_price` DECIMAL(10,0) DEFAULT NULL,
  `mfn_listing_exists` VARCHAR(255) DEFAULT NULL,
  `mfn_fulfillable_quantity` VARCHAR(255) DEFAULT NULL,
  `afn_listing_exists` VARCHAR(255) DEFAULT NULL,
  `afn_warehouse_quantity` INT(11) DEFAULT NULL,
  `afn_fulfillable_quantity` INT(11) DEFAULT NULL,
  `afn_unsellable_quantity` INT(11) DEFAULT NULL,
  `afn_reserved_quantity` INT(11) DEFAULT NULL,
  `afn_total_quantity` INT(11) DEFAULT NULL,
  `per_unit_volume` DECIMAL(10,0) DEFAULT NULL,
  `afn_inbound_working_quantity` INT(11) DEFAULT NULL,
  `afn_inbound_shipped_quantity` INT(11) DEFAULT NULL,
  `afn_inbound_receiving_quantity` INT(11) DEFAULT NULL,
  `seller_id` VARCHAR(255) NOT NULL,
  `user_id` INT(11) DEFAULT NULL,
  `username` VARCHAR(255) DEFAULT NULL,
  `site` VARCHAR(255) DEFAULT NULL,
  `img_url` TEXT,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `report_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '报告时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8

ALTER TABLE `sku_inventory` ADD COLUMN `process_id` BIGINT NULL COMMENT '在研精品' AFTER `username`;
ALTER TABLE `sku_inventory_history` ADD COLUMN `process_id` BIGINT NULL COMMENT '在研精品' AFTER `username`; 
ALTER TABLE `sku_inventory` ADD COLUMN `sales_start_time` VARCHAR(10) NULL COMMENT '参考销量开始时间' AFTER `express_type`, ADD COLUMN `sales_end_time` VARCHAR(10) NULL COMMENT '参考销量结束时间' AFTER `sales_start_time`; 

/**
 * 添加菜单权限
 */
INSERT INTO sys_menu( parent_id, parent_ids, name, href, target, icon, sort, is_show, permission, create_date, del_flag ) VALUES ( 132, '0,1,108,132,', '备货计划修改', '', null, '', 100, 0, 'stockUp:plan:edit', NOW(), '0' )
INSERT INTO sys_menu( parent_id, parent_ids, name, href, target, icon, sort, is_show, permission, create_date, del_flag ) VALUES ( 132, '0,1,108,132,', '包装时间修改', '', null, '', 200, 0, 'stockUp:packTime:edit', NOW(), '0' )


/**
*    周报
**/
DROP TABLE IF EXISTS `weekly`;
CREATE TABLE `weekly` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rank` int(11) DEFAULT NULL,
  `brush_list_number` int(11) DEFAULT NULL COMMENT '刷单数',
  `ad_brush_list_number` int(11) DEFAULT NULL COMMENT '广告刷单数',
  `brush_list_sum` int(11) DEFAULT NULL COMMENT '历史总刷单数',
  `brush_list_duration` int(11) DEFAULT NULL COMMENT '刷单时长',
  `advertising_costs` double(8,2) DEFAULT NULL COMMENT '广告费用',
  `acos` varchar(255) DEFAULT NULL COMMENT 'ACOS',
  `ld_costs` double(8,2) DEFAULT NULL COMMENT 'LD费用',
  `review_increment` int(11) DEFAULT NULL COMMENT 'review增长数',
  `low_sales_summary` varchar(255) DEFAULT NULL COMMENT '美国日均低于5单原因总结（日本3单）',
  `promotion_plan` varchar(255) DEFAULT NULL COMMENT '推广计划',
  `activity` varchar(255) DEFAULT NULL COMMENT '是否站外报促销或者活动，详细列下安排轨迹（包括红人刷单数，哪些平台报活动之类）',
  `defective_number` int(11) DEFAULT NULL COMMENT '次品数',
  `defective_cause` varchar(255) DEFAULT NULL COMMENT '次品原因',
  `defective_rate` double(2,2) DEFAULT NULL COMMENT '次品率',
  `process_id` bigint(20) NOT NULL COMMENT '在研精品id',
  `sku` varchar(50) NOT NULL COMMENT 'sku',
  `seller_id` varchar(50) NOT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `asin` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


INSERT INTO `sys_dict` (`value`, `label`, `type`, `sort`) VALUES ('90', '默认销量范围', 'defaultSalesTime', '10'); 

ALTER TABLE `weekly` ADD COLUMN `status` INT(1) DEFAULT 0 NOT NULL COMMENT '状态 0:草稿 1:已提交' AFTER `asin`; 

/**
 * 日报填写
 */
CREATE TABLE `daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `process_id` bigint(20) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `seller_id` varchar(50) DEFAULT NULL,
  `asin` varchar(20) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `rank` int(11) NOT NULL,
  `small_class_rank` int(11) NOT NULL COMMENT '小类rank',
  `acos` varchar(255) DEFAULT NULL,
  `brush_list_situation` varchar(255) DEFAULT NULL COMMENT '刷单情况',
  `other_situation` varchar(255) DEFAULT NULL COMMENT '其他',
  `advertising_costs` double(8,2) DEFAULT NULL COMMENT '广告费用',
  `remarks` varchar(255) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0:草稿 1:已提交',
  `update_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

ALTER TABLE `mkt_process` ADD COLUMN `whether_daily` TINYINT(1) DEFAULT 0 NOT NULL COMMENT '1：无需填写日报' AFTER `produce_cycle`; 

/**
 * 库存历史
 */
CREATE TABLE `inventory_health_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `snapshot_date` varchar(255) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `fnsku` varchar(255) DEFAULT NULL,
  `asin` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `condition` varchar(10) DEFAULT NULL,
  `sales_rank` varchar(255) DEFAULT NULL,
  `product_group` varchar(255) DEFAULT NULL,
  `total_quantity` varchar(255) DEFAULT NULL,
  `sellable_quantity` int(11) DEFAULT NULL,
  `unsellable_quantity` int(11) DEFAULT NULL,
  `inv_age_0_to_90_days` int(11) DEFAULT NULL,
  `inv_age_91_to_180_days` int(11) DEFAULT NULL,
  `inv_age_181_to_270_days` int(11) DEFAULT NULL,
  `inv_age_271_to_365_days` int(10) DEFAULT NULL,
  `inv_age_365_plus_days` int(11) DEFAULT NULL,
  `units_shipped_last_24_hrs` int(11) DEFAULT NULL,
  `units_shipped_last_7_days` int(11) DEFAULT NULL,
  `units_shipped_last_30_days` varchar(255) DEFAULT NULL,
  `units_shipped_last_90_days` varchar(255) DEFAULT NULL,
  `units_shipped_last_180_days` varchar(255) DEFAULT NULL,
  `units_shipped_last_365_days` varchar(255) DEFAULT NULL,
  `weeks_of_cover_t7` varchar(255) DEFAULT NULL,
  `weeks_of_cover_t30` varchar(255) DEFAULT NULL,
  `weeks_of_cover_t90` varchar(255) DEFAULT NULL,
  `weeks_of_cover_t180` varchar(255) DEFAULT NULL,
  `weeks_of_cover_t365` varchar(255) DEFAULT NULL,
  `num_afn_new_sellers` varchar(255) DEFAULT NULL,
  `num_afn_used_sellers` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `your_price` varchar(255) DEFAULT NULL,
  `sales_price` varchar(255) DEFAULT NULL,
  `lowest_afn_new_price` varchar(255) DEFAULT NULL,
  `lowest_afn_used_price` varchar(255) DEFAULT NULL,
  `lowest_mfn_new_price` varchar(255) DEFAULT NULL,
  `lowest_mfn_used_price` varchar(255) DEFAULT NULL,
  `qty_to_be_charged_ltsf_12_mo` varchar(255) DEFAULT NULL,
  `qty_in_long_term_storage_program` varchar(255) DEFAULT NULL,
  `qty_with_removals_in_progress` varchar(255) DEFAULT NULL,
  `projected_ltsf_12_mo` varchar(255) DEFAULT NULL,
  `per_unit_volume` varchar(255) DEFAULT NULL,
  `is_hazmat` varchar(255) DEFAULT NULL,
  `in_bound_quantity` varchar(255) DEFAULT NULL,
  `asin_limit` varchar(255) DEFAULT NULL,
  `inbound_recommend_quantity` varchar(255) DEFAULT NULL,
  `qty_to_be_charged_ltsf_6_mo` varchar(255) DEFAULT NULL,
  `projected_ltsf_6_mo` varchar(255) DEFAULT NULL,
  `seller_id` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `img_url` text,
  process_id bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `report_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '报告时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT


 ALTER TABLE `daily` ADD COLUMN `reviews` INT NULL AFTER `advertising_costs`, ADD COLUMN `ld_costs` VARCHAR(100) NULL AFTER `reviews`; 
 ALTER TABLE `daily` ADD COLUMN `sales_volume` INT NULL COMMENT '销量' AFTER `ld_costs`; 
 ALTER TABLE `daily` ADD COLUMN `product_id` INT NOT NULL AFTER `img_url`; 
 ALTER TABLE `weekly` ADD COLUMN `product_id` INT NOT NULL AFTER `asin`; 
 ALTER TABLE `mkt_process_keyword_rank_history` ADD COLUMN `location` VARCHAR(100) NULL AFTER `rank`; 
 ALTER TABLE `mkt_process_keyword_rank_history` ADD INDEX `pkr_index` (`daily_id`);
 
 /**
 * 店铺管理
 */
 CREATE TABLE `shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `seller_id` varchar(20) NOT NULL,
  `marketplace_id` varchar(30) NOT NULL COMMENT '站点',
  `name` varchar(255) NOT NULL COMMENT '店铺名',
  `remote_address` varchar(100) DEFAULT NULL COMMENT '远程地址',
  `closed_time` varchar(100) DEFAULT NULL COMMENT '被封时间',
  `closed_reason` varchar(255) DEFAULT NULL COMMENT '被封原因',
  `is_appeal` tinyint(1) NOT NULL DEFAULT '0' COMMENT '可否申诉',
  `balance` double(10,2) DEFAULT NULL COMMENT '店铺balance',
  `freeze_time` varchar(100) DEFAULT NULL COMMENT '冻结时长',
  `is_bind_card` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑收款卡',
  `card_tail_number` varchar(10) DEFAULT NULL COMMENT '卡尾号',
  `transfer_time` datetime DEFAULT NULL COMMENT '转账时间',
  `last_appeal_time` varchar(50) DEFAULT NULL COMMENT '最后申诉时间',
  `no_transfer_on_time_remarks` varchar(255) DEFAULT NULL COMMENT '未准时转账原因',
  `aggregate_amount` double(10,2) DEFAULT NULL COMMENT '汇总金额',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8

ALTER TABLE `weekly` ADD COLUMN `defective_duty` TINYINT(1) NULL COMMENT '次品责任方 1:采购 2:客户' AFTER `activity`; 

/**
*   百科和侵权词
**/
CREATE TABLE `kb_infringement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `type` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `update_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='侵权库'

CREATE TABLE `kb_encyclopedia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `type` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '内容',
  `remarks` varchar(100) DEFAULT NULL,
  `update_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='百科'

ALTER TABLE `sys_approve` ADD COLUMN `afn_fulfillable_quantity` VARCHAR(20) NULL COMMENT '可用库存数' AFTER `purchase_remarks`, ADD COLUMN `on_the_way_quantity` VARCHAR(20) NULL COMMENT '在途' AFTER `afn_fulfillable_quantity`;