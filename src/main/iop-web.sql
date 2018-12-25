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
  `authc_id` int(11) NOT NULL AUTO_INCREMENT,
  `authc_name` varchar(256) NOT NULL,
  `authc_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`authc_id`),
  UNIQUE KEY `authc_name` (`authc_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_authc` */

insert  into `iop_sys_authc`(`authc_id`,`authc_name`,`authc_desc`) values (1,'sys:helloWord:openPage','测试1'),(2,'sys:helloWord:testAjax','测试2');

/*Table structure for table `iop_sys_menu` */

DROP TABLE IF EXISTS `iop_sys_menu`;

CREATE TABLE `iop_sys_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(64) NOT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `parent_mennu_id` varchar(1024) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`,`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_menu` */

/*Table structure for table `iop_sys_role` */

DROP TABLE IF EXISTS `iop_sys_role`;

CREATE TABLE `iop_sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) NOT NULL,
  `role_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role` */

insert  into `iop_sys_role`(`role_id`,`role_name`,`role_desc`) values (1,'超级管理员','拥有最多的权限，该角色属于最高级管理员'),(2,'权限管理员','具有权限分配权利');

/*Table structure for table `iop_sys_role_authc` */

DROP TABLE IF EXISTS `iop_sys_role_authc`;

CREATE TABLE `iop_sys_role_authc` (
  `role_authc_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_authc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role_authc` */

insert  into `iop_sys_role_authc`(`role_authc_id`,`role_id`,`authc_id`) values (1,1,1),(2,1,2),(3,2,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user` */

insert  into `iop_sys_user`(`user_id`,`username`,`nickname`,`password`) values (1,'admin','超级管理员','123456'),(2,'authcAdmin','权限管理员','123456');

/*Table structure for table `iop_sys_user_role` */

DROP TABLE IF EXISTS `iop_sys_user_role`;

CREATE TABLE `iop_sys_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user_role` */

insert  into `iop_sys_user_role`(`user_role_id`,`user_id`,`role_id`) values (1,1,1),(2,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
