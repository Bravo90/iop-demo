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

/*Table structure for table `iop_hello_world` */

DROP TABLE IF EXISTS `iop_hello_world`;

CREATE TABLE `iop_hello_world` (
  `hw_id` int(11) NOT NULL,
  `hw_name` varchar(256) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `developers` varchar(2048) DEFAULT NULL,
  `contributors` varchar(2048) DEFAULT NULL,
  `company` varchar(256) DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `e_mail` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`hw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_hello_world` */

insert  into `iop_hello_world`(`hw_id`,`hw_name`,`description`,`developers`,`contributors`,`company`,`tel`,`e_mail`) values (1,'智慧运营系统','电信行业最牛逼的运营管理、监控系统','智慧运营团队：xx','apache、alibaba','北京思特奇科技技术有限公司','010-xxxx-xxxx-xxxx','sitech@site-ch.com.cn'),(2,'前端组','智慧运营团队前端组','sunzhen','sunzhen','sitech','1989898981','sunzhen@si-tech.com.cn');

/*Table structure for table `iop_sys_authc` */

DROP TABLE IF EXISTS `iop_sys_authc`;

CREATE TABLE `iop_sys_authc` (
  `authc_id` int(11) NOT NULL,
  `authc_name` varchar(256) NOT NULL,
  PRIMARY KEY (`authc_id`,`authc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_authc` */

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
  `role_id` int(11) NOT NULL,
  `role_name` varchar(16) NOT NULL,
  `role_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role` */

/*Table structure for table `iop_sys_user` */

DROP TABLE IF EXISTS `iop_sys_user`;

CREATE TABLE `iop_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `nickname` varchar(16) DEFAULT NULL,
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`user_id`,`username`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
