/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.21 : Database - iop-web
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iop-web` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `iop-web`;

/*Table structure for table `iop_sys_authc` */

DROP TABLE IF EXISTS `iop_sys_authc`;

CREATE TABLE `iop_sys_authc` (
  `authc_id` int(11) NOT NULL,
  `authc_name` varchar(256) NOT NULL,
  `authc_desc` varchar(1024) NOT NULL,
  PRIMARY KEY (`authc_id`),
  UNIQUE KEY `authc_name` (`authc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_authc` */

insert  into `iop_sys_authc`(`authc_id`,`authc_name`,`authc_desc`) values (1001,'sys:user:page','用户管理界面'),(1002,'sys:role:page','角色管理界面'),(1003,'sys:authc:page','权限管理界面'),(1004,'sys:menu:page','菜单管理界面'),(1005,'sys:demo:page','演示界面'),(1001001,'sys:user:list','用户列表查询'),(1001002,'sys:user:single','单个用户查询'),(1001003,'sys:user:add','用户添加'),(1001004,'sys:user:update','用户更新'),(1001005,'sys:user:delete','用户删除'),(1001006,'sys:user:role','查看角色'),(1001007,'sys:user:assign','角色分配'),(1002001,'sys:role:list','角色列表查询'),(1002002,'sys:role:single','单个角色查询'),(1002003,'sys:role:add','角色添加'),(1002004,'sys:role:delete','角色删除'),(1002005,'sys:role:update','角色更新'),(1002006,'sys:role:authc','查看角色权限'),(1002007,'sys:role:assign','角色权限分配'),(1003001,'sys:authc:list','权限列表查询'),(1003002,'sys:authc:single','单个权限查询'),(1003003,'sys:authc:add','权限添加'),(1003004,'sys:authc:delete','权限删除'),(1003005,'sys:authc:update','权限更新');

/*Table structure for table `iop_sys_menu` */

DROP TABLE IF EXISTS `iop_sys_menu`;

CREATE TABLE `iop_sys_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(64) NOT NULL,
  `menu_url` varchar(2048) DEFAULT '#',
  `menu_level` int(11) DEFAULT NULL,
  `parent_mennu_id` varchar(1024) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `authc_Id` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`,`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_menu` */

insert  into `iop_sys_menu`(`menu_id`,`menu_name`,`menu_url`,`menu_level`,`parent_mennu_id`,`menu_order`,`authc_Id`) values (1001,'系统管理','#',1,'0',1,0),(2001,'用户管理','/user/page',2,'1001',1,1001),(2002,'角色管理','/role/page',2,'1001',2,1002),(2003,'权限管理','/authc/page',2,'1001',3,1003),(2004,'菜单管理','/menu/page',2,'1001',4,1004);

/*Table structure for table `iop_sys_role` */

DROP TABLE IF EXISTS `iop_sys_role`;

CREATE TABLE `iop_sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) NOT NULL,
  `role_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role` */

insert  into `iop_sys_role`(`role_id`,`role_name`,`role_desc`) values (1,'超级管理员','具有所有权限'),(2,'权限管理员','具有权限分配权利'),(6,'普通用户','具有部分权限'),(8,'测试用户','测试用户账号'),(9,'分支用户','分支用户，具有部分权限');

/*Table structure for table `iop_sys_role_authc` */

DROP TABLE IF EXISTS `iop_sys_role_authc`;

CREATE TABLE `iop_sys_role_authc` (
  `role_authc_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_authc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role_authc` */

insert  into `iop_sys_role_authc`(`role_authc_id`,`role_id`,`authc_id`) values (32,2,1001001),(33,2,1001002),(34,2,1001003),(35,2,1001004),(36,2,1001005),(37,2,1001006),(38,2,1001007),(39,2,1002),(40,2,1002001),(41,2,1002002),(42,2,1002003),(43,2,1002004),(44,2,1002005),(45,2,1002006),(46,2,1002007),(47,2,1003),(48,2,1003001),(49,2,1003002),(50,2,1003003),(51,2,1003004),(52,2,1003005),(64,1,1001001),(65,1,1001002),(66,1,1001003),(67,1,1001004),(68,1,1001005),(69,1,1001006),(70,1,1001007),(71,1,1002),(72,1,1002001),(73,1,1002002),(74,1,1002003),(75,1,1002004),(76,1,1002005),(77,1,1002006),(78,1,1002007),(79,1,1003),(80,1,1003001),(81,1,1003002),(82,1,1003003),(83,1,1003004),(84,1,1003005),(92,1,1004),(93,1,1005),(95,6,1001001),(96,6,1001006),(97,6,1002),(98,6,1002001),(100,6,1002006),(101,6,1003),(102,6,1003001),(104,1,1001),(105,6,1001),(106,2,1001);

/*Table structure for table `iop_sys_user` */

DROP TABLE IF EXISTS `iop_sys_user`;

CREATE TABLE `iop_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `nickname` varchar(16) DEFAULT '',
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user` */

insert  into `iop_sys_user`(`user_id`,`username`,`nickname`,`password`) values (1,'admin','超级管理员','21232f297a57a5a743894a0e4a801fc3'),(2,'authcAdmin','权限管理员','e10adc3949ba59abbe56e057f20f883e'),(39,'sunzhen','开发人员','e10adc3949ba59abbe56e057f20f883e'),(40,'bravo','测试人员','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `iop_sys_user_role` */

DROP TABLE IF EXISTS `iop_sys_user_role`;

CREATE TABLE `iop_sys_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user_role` */

insert  into `iop_sys_user_role`(`user_role_id`,`user_id`,`role_id`) values (16,2,2),(17,39,6),(20,40,8),(21,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
